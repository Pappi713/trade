package com.greenfoxacademy.trade.service;


import com.greenfoxacademy.trade.model.User;
import com.greenfoxacademy.trade.repository.UserRepository;
import com.greenfoxacademy.trade.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);
        optionalUser
            .orElseThrow(() -> new UsernameNotFoundException("No user with username " + username + "has been found."));
        return optionalUser.map(MyUserDetails::new).get();
    }
}
