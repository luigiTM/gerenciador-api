package com.br.luigi.gerenciador.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.br.luigi.gerenciador.dominio.Cliente;
import com.br.luigi.gerenciador.dominio.Pedido;

public class PedidoDTO {

	private Integer id;
	private Cliente cliente;
	private Float totalCompra;
	@NotEmpty(message = "Data da compra não pode ser vazio")
	private Date dataCompra;

	public PedidoDTO() {
	}

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.cliente = pedido.getCliente();
		this.totalCompra = pedido.getTotalCompra();
		this.dataCompra = pedido.getDataCompra();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Float totalCompra) {
		this.totalCompra = totalCompra;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

}
