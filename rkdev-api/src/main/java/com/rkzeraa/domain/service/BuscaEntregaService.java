package com.rkzeraa.domain.service;

import org.springframework.stereotype.Service;

import com.rkzeraa.domain.exception.EntidadeNaoEncontradaException;
import com.rkzeraa.domain.model.Entrega;
import com.rkzeraa.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
