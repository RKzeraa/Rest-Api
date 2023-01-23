package com.rkzeraa.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.rkzeraa.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaResponse {
	
	private Long id;
	private String nomeCliente;
	private DestinatarioResponse destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
}
