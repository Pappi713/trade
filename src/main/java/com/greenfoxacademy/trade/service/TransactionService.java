package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.dto.HistoryResponseDTO;
import com.greenfoxacademy.trade.dto.TransactionRequestDto;
import com.greenfoxacademy.trade.dto.TransactionResponseDto;
import com.greenfoxacademy.trade.exception.InsufficientFundsException;
import com.greenfoxacademy.trade.exception.NoSuchStockException;
import com.greenfoxacademy.trade.exception.PositionAlreadyClosedException;
import com.greenfoxacademy.trade.exception.UserNotFoundException;
import com.greenfoxacademy.trade.model.OwnedStock;
import com.greenfoxacademy.trade.model.Transaction;
import com.greenfoxacademy.trade.model.User;
import com.greenfoxacademy.trade.repository.OwnedStockRepository;
import com.greenfoxacademy.trade.repository.TransactionRepository;
import com.greenfoxacademy.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;
  private UserRepository userRepository;
  private OwnedStockRepository ownedStockRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository,
                            UserRepository userRepository,
                            OwnedStockRepository ownedStockRepository){
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
    this.ownedStockRepository = ownedStockRepository;
  }

  public List<HistoryResponseDTO> transactionHistory(Principal principal) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findById(principal.getName());
    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException("Not a valid user");
    }
    User user = optionalUser.get();
    return user.getTransactions().stream()
        .map(i-> new HistoryResponseDTO(i.getType(),i.getDate(),i.getStockPrice(),i.getAmount(),i.getTransactionValue(),i.getTransactionType(), i.getProfit()))
        .collect(Collectors.toList());
  }

  public TransactionResponseDto makeTransaction(TransactionRequestDto requestDto, String username)
      throws UserNotFoundException, NoSuchStockException, InsufficientFundsException, PositionAlreadyClosedException {
    Optional<User> optionalUser = userRepository.findById(username);
    if(!optionalUser.isPresent()) {
      throw new UserNotFoundException("User not found with the following username: " + username);
    }
    User user = optionalUser.get();
    if(requestDto.getOrder().equals("Sell")){
      return makeSellTransaction(requestDto, user);
    }
    return makeBuyTransaction(requestDto, user);
  }

  public TransactionResponseDto makeSellTransaction(TransactionRequestDto requestDto, User user)
      throws NoSuchStockException, PositionAlreadyClosedException {
    checkStockBalance(requestDto);
    OwnedStock ownedStock = getOwnedStockByTransactionRequestDto(requestDto);
    String date = getFormattedDate();
    Double transactionValue = requestDto.getAmount() * requestDto.getPrice();
    Double newBalance = user.getBalance() + transactionValue;
    Transaction transaction = new Transaction(requestDto.getType(),
        date,
        requestDto.getPrice(),
        transactionValue,
        requestDto.getAmount(),
        "Sell",
        transactionValue - requestDto.getAmount() * ownedStock.getBuyInPrice(),
        user);
    user.setBalance(newBalance);
    ownedStock.setAmount(ownedStock.getAmount() - requestDto.getAmount());
    ownedStockRepository.save(ownedStock);
    userRepository.save(user);
    transactionRepository.save(transaction);
    return new TransactionResponseDto(newBalance, transaction);
  }

  public TransactionResponseDto makeBuyTransaction(TransactionRequestDto requestDto, User user)
      throws InsufficientFundsException {
    checkBalance(user, requestDto);
    String date = getFormattedDate();
    Double transactionValue = requestDto.getAmount() * requestDto.getPrice();
    Double newBalance = user.getBalance() - transactionValue;
    Transaction transaction = new Transaction(requestDto.getType(), date, requestDto.getPrice(), transactionValue,
        requestDto.getAmount(), "Buy", user);
    user.setBalance(newBalance);
    OwnedStock ownedStock = new OwnedStock(transaction.getType(), transaction.getAmount(), transaction.getStockPrice(), user);
    ownedStockRepository.save(ownedStock);
    userRepository.save(user);
    transactionRepository.save(transaction);
    return new TransactionResponseDto(newBalance, transaction);
  }

  public String getFormattedDate() {
    String pattern = "yyyy-MM-dd HH:mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(new Date());
  }

  public void checkStockBalance (TransactionRequestDto requestDto)
      throws PositionAlreadyClosedException, NoSuchStockException {
    Integer sellAmount = requestDto.getAmount();
    Long id = (long) requestDto.getID();
    Optional<OwnedStock> optionalOwnedStock = ownedStockRepository.findById(id);
    if(!optionalOwnedStock.isPresent()) {
      throw new NoSuchStockException();
    }
    if(optionalOwnedStock.get().getAmount() < sellAmount) {
      throw new PositionAlreadyClosedException();
    }
  }

  public void checkBalance(User user, TransactionRequestDto requestDto) throws InsufficientFundsException {
    Double balance = user.getBalance();
    Double transactionValue = requestDto.getPrice() * requestDto.getAmount();
    if(balance < transactionValue) {
      throw new InsufficientFundsException();
    }
  }

  public OwnedStock getOwnedStockByTransactionRequestDto(TransactionRequestDto requestDto) throws NoSuchStockException {
    Optional<OwnedStock> optionalOwnedStock = ownedStockRepository.findById((long) requestDto.getID());
    if(optionalOwnedStock.isPresent()){
      return optionalOwnedStock.get();
    }
    throw new NoSuchStockException();
  }
}