package com.rodrigo.desafiojava.repository;


import com.rodrigo.desafiojava.domain.Phones;
import com.rodrigo.desafiojava.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, Long> {

       Optional<Phones> findById(Long id);



}
