package com.rodrigo.desafiojava.domain;


import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phones {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddd;
    private String number;

    public String getDdd() {
        return ddd;
    }

    public String getNumber() {
        return number;
    }
}
