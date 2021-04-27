package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.dto.OwnedStockResponseDTO;
import com.greenfoxacademy.trade.exception.UserNotFoundException;
import com.greenfoxacademy.trade.model.User;
import com.greenfoxacademy.trade.repository.OwnedStockRepository;
import com.greenfoxacademy.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnedStockService {

  private OwnedStockRepository ownedStockRepository;
  private UserRepository userRepository;

  @Autowired
  public OwnedStockService(OwnedStockRepository ownedStockRepository, UserRepository userRepository) {
    this.ownedStockRepository = ownedStockRepository;
    this.userRepository = userRepository;
  }

  public List<OwnedStockResponseDTO> ownedStocks(Principal principal) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findById(principal.getName());
    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException("Not a valid user");
    }
    User user = optionalUser.get();
    return user.getPortfolio().stream().filter(i->i.getAmount()>0)
        .map(i -> new OwnedStockResponseDTO(i.getId(), i.getAmount(), i.getType(), i.getBuyInPrice()))
        .collect(Collectors.toList());
  }
}
