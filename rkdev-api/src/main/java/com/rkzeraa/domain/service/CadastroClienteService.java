package com.rkzeraa.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rkzeraa.domain.exception.NegocioException;
import com.rkzeraa.domain.model.Cliente;
import com.rkzeraa.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
		
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailExistente) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail!");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
