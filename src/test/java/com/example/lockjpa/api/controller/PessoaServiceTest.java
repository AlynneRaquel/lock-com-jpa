package com.example.lockjpa.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.lockjpa.domain.model.Pessoa;
import com.example.lockjpa.domain.repository.PessoaRepository;
import com.example.lockjpa.domain.service.PessoaService;

import jakarta.persistence.OptimisticLockException;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	@Test
	public void testAtualizarPessoa_OptimisticLockException() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setPrimeiroNome("Alynne");
		pessoa.setSobrenome("Pereira");
		pessoa.setVersion(1L);
		
		when(pessoaRepository.save(any(Pessoa.class)))
			.thenThrow(OptimisticLockException.class);
		
		try {
			pessoaService.atualizarPessoa(pessoa);
			fail("OptimisticLockException deveria ter sido lançada");
		} catch (OptimisticLockException e) {
			assertNotNull(e);
		}
		
		verify(pessoaRepository, times(1)).save(pessoa);
	}
}
