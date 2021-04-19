package com.rodrigo.desafiojava.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phones {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Size( min = 2 , max=3)
    private String ddd;
    @Size(min = 8, max=9)
    private String number;


}
