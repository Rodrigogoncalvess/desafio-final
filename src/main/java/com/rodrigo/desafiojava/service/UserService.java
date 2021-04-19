package com.rodrigo.desafiojava.service;


import com.rodrigo.desafiojava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


      Optional<com.rodrigo.desafiojava.domain.User> user = userRepository.findByEmail(s);
       if (user.isEmpty()){
           return null;
       }
        // Logic to get the user form the Database
        return user.get();
    }
}
