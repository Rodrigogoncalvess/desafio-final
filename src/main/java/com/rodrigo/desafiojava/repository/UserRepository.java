package com.rodrigo.desafiojava.repository;


import com.rodrigo.desafiojava.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

       Optional<User> findById(UUID uuid);
       Optional<User> findByEmail(String email);
       Optional<User> findByToken(String token);

       boolean existsByEmail(String email);


}
