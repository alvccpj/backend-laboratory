package com.backend.lab.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa_db")
@Data
public class Pessoa {

    @Id
    private String cpf;

    private String name;

    private int idade;
}

