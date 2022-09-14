package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Estado com Id: %d não cadastrado", estadoId));
		}
		
		cidade.setEstado(estado.get());
		
		return cidadeRepository.save(cidade);
	}
	
	public void remover(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com código %d não contrada", cidadeId));
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade com código %d está em uso", cidadeId));
		}
	}
	
}
