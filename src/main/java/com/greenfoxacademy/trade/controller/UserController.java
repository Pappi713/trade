package com.greenfoxacademy.trade.controller;

import com.greenfoxacademy.trade.dto.LoginDTO;
import com.greenfoxacademy.trade.dto.LoginResponseDTO;
import com.greenfoxacademy.trade.dto.ResponseDTO;
import com.greenfoxacademy.trade.exception.UserAlreadyExistsException;
import com.greenfoxacademy.trade.exception.UserNotFoundException;
import com.greenfoxacademy.trade.security.JwtUtil;
import com.greenfoxacademy.trade.service.MyUserDetailsService;
import com.greenfoxacademy.trade.service.OwnedStockService;
import com.greenfoxacademy.trade.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final MyUserDetailsService myUserDetailsService;
  private final JwtUtil jwtUtil;
  private OwnedStockService ownedStockService;

  @Autowired
  public UserController(UserService userService,
                        AuthenticationManager authenticationManager,
                        MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil, OwnedStockService ownedStockService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.myUserDetailsService = myUserDetailsService;
    this.jwtUtil = jwtUtil;
    this.ownedStockService =ownedStockService;
  }


  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<?> register(@RequestBody LoginDTO loginDTO) throws UserAlreadyExistsException {
    userService.createUser(loginDTO);
    ResponseDTO dto = new ResponseDTO("registration successful");
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect or missing username or password", e);
    }
    String username = authenticationRequest.getUsername();
    final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
    final String jwt = jwtUtil.generateToken(userDetails);
    LoginResponseDTO response = userService.createLoginResponse(username, jwt);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/portfolio")
  @ResponseBody
  public ResponseEntity<?> getPortfolio(Principal principal) throws UserNotFoundException {
    return ResponseEntity.ok(ownedStockService.ownedStocks(principal));
  }
}
