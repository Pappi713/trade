package com.greenfoxacademy.trade.service;

import com.greenfoxacademy.trade.dto.LoginDTO;
import com.greenfoxacademy.trade.dto.LoginResponseDTO;
import com.greenfoxacademy.trade.exception.UserAlreadyExistsException;
import com.greenfoxacademy.trade.exception.UserNotFoundException;
import com.greenfoxacademy.trade.model.User;
import com.greenfoxacademy.trade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository=userRepository;
  }

  public User createUser(LoginDTO loginDTO) throws UserAlreadyExistsException {
    String username = loginDTO.getUsername();
    if(existsByUsername(username)) {
      throw new UserAlreadyExistsException("The following username is already taken: " + username);
    }
    return userRepository.save(new User(username, loginDTO.getPassword(), 0D));
  }

  public LoginResponseDTO createLoginResponse(String username, String jwt) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findById(username);
    if(!optionalUser.isPresent()) {
      throw new UserNotFoundException("User does not exist with the given username");
    }
    User user = optionalUser.get();
    return new LoginResponseDTO(jwt, user.getBalance());
  }

  public Boolean existsByUsername(String username) {
    return userRepository.existsById(username);
  }
}
