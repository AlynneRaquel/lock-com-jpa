package com.example.lockjpa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lockjpa.domain.model.Pessoa;
import com.example.lockjpa.domain.repository.PessoaRepository;
import com.example.lockjpa.domain.service.PessoaService;



@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long produtoId, 
			 @RequestBody Pessoa pessoa) {
		if(!pessoaRepository.existsById(produtoId)) {
			return ResponseEntity.notFound().build();
		}
		
		pessoa.setId(produtoId);
		pessoa = pessoaService.atualizarPessoa(pessoa); 
		return ResponseEntity.ok(pessoa);
	}
	

	
	

}
