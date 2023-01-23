package com.rkzeraa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rkzeraa.api.model.EntregaResponse;
import com.rkzeraa.api.model.input.EntregaInput;
import com.rkzeraa.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {
	
	private ModelMapper modelMapper;
	
	public EntregaResponse toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaResponse.class);
	}
	
	public List<EntregaResponse> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
