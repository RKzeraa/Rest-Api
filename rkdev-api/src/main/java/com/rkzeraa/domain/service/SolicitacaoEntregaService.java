package com.rkzeraa.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rkzeraa.domain.model.Cliente;
import com.rkzeraa.domain.model.Entrega;
import com.rkzeraa.domain.model.StatusEntrega;
import com.rkzeraa.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	private CadastroClienteService cadastroClienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
