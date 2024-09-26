package com.backend.lab.controller;

import com.backend.lab.dto.PessoaDto;
import com.backend.lab.model.Pessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.lab.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        Pessoa savedPessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> allPessoas = pessoaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPessoas);
    }

    @GetMapping("/pessoas/{cpf}")
    public ResponseEntity<Object> getPessoaByCPF(@PathVariable String cpf) {
        Optional<Pessoa> foundPessoa = pessoaRepository.findById(cpf);
        if (foundPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundPessoa.get());
    }

    @DeleteMapping("/pessoas/{cpf}")
    public ResponseEntity<String> deletePessoaByCPF(@PathVariable String cpf) {
        Optional<Pessoa> foundPessoa = pessoaRepository.findById(cpf);
        if (foundPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }
        pessoaRepository.delete(foundPessoa.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted successfully!");
    }

    @PutMapping("/pessoas/{cpf}")
    public ResponseEntity<Object> updatePessoaByCPF(@RequestBody PessoaDto pessoaDto,
                                                    @PathVariable String cpf) {
        Optional<Pessoa> foundPessoa = pessoaRepository.findById(cpf);
        if (foundPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }

        Pessoa pessoa = foundPessoa.get();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoa));
    }
}
