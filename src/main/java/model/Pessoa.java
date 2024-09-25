package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa_db")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    private String cpf;

    private int idade;
}
