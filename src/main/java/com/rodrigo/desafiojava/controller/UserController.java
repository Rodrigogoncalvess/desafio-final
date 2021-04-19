package com.rodrigo.desafiojava.controller;


import com.rodrigo.desafiojava.Utilty.JWTUtility;
import com.rodrigo.desafiojava.domain.JwtRequest;
import com.rodrigo.desafiojava.domain.Phones;
import com.rodrigo.desafiojava.domain.User;
import com.rodrigo.desafiojava.exceptionhandler.ReplyMessage;
import com.rodrigo.desafiojava.repository.PhonesRepository;
import com.rodrigo.desafiojava.repository.UserRepository;
import com.rodrigo.desafiojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    private ResponseEntity<?> create(@Valid @RequestBody User user,Phones phones){

        Optional<User> userExistsByEmail = userRepository.findByEmail(user.getEmail());

        if (!userExistsByEmail.isPresent()) {
            user.encryptPassword();
            user.setCreated(LocalDateTime.now());
            user.setModified(LocalDateTime.now());
            user.setLast_login(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        }

        return ResponseEntity.badRequest().body(new ReplyMessage("Usuário já existe"));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {

        Optional<User> userExistsByEmail = userRepository.findByEmail(user.getEmail());
        if (userExistsByEmail.isPresent()) {
            if (new BCryptPasswordEncoder().matches(user.getPassword(), userExistsByEmail.get().getPassword())) {
                final String token = jwtUtility.generateToken(userExistsByEmail.get());
                userExistsByEmail.get().setToken(token);
                userExistsByEmail.get().setLast_login(LocalDateTime.now());
                return new ResponseEntity<User>( userRepository.save(userExistsByEmail.get()), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReplyMessage("Usuário e/ou senha inválidos"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReplyMessage("Usuário e/ou senha inválidos"));
    }


    @GetMapping("/getall")
    private List<?> getall() {
        return userRepository.findAll();
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws Exception {


        if (!userRepository.existsById(user.getUuid())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReplyMessage("UUID não existe"));

        }
        user.setModified(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ReplyMessage("Atualizado com sucesso"));
    }



    @DeleteMapping("/{uuid}")
    private ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        if (!userRepository.existsById(uuid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReplyMessage("Usuário não existe"));
        }
        userRepository.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ReplyMessage("Usuário excluído com sucesso"));


    }


}

