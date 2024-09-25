package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import repository.PessoaRepository;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;
}
