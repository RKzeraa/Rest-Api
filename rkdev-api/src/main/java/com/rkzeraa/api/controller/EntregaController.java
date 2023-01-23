package com.rkzeraa.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rkzeraa.api.mapper.EntregaMapper;
import com.rkzeraa.api.model.EntregaResponse;
import com.rkzeraa.api.model.input.EntregaInput;
import com.rkzeraa.domain.model.Entrega;
import com.rkzeraa.domain.repository.EntregaRepository;
import com.rkzeraa.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaMapper entregaMapper;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaResponse solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return entregaMapper.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaResponse> listar() {
		return entregaMapper.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaResponse> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}
