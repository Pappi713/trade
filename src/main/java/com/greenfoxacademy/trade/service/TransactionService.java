package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.dto.HistoryResponseDTO;
import com.greenfoxacademy.trade.exception.UserNotFoundException;
import com.greenfoxacademy.trade.model.User;
import com.greenfoxacademy.trade.repository.TransactionRepository;
import com.greenfoxacademy.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;
  private UserRepository userRepository;

  @Autowired
  public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository){
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
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
}
