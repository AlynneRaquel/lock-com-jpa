package com.example.lockjpa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lockjpa.domain.model.Pessoa;
import com.example.lockjpa.domain.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa atualizarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

}
