package com.br.luigi.gerenciador.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.br.luigi.gerenciador.dominio.Produto;
import com.br.luigi.gerenciador.servicos.validacao.ProdutoInsercao;

@ProdutoInsercao
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	@NotEmpty(message = "SKU não pode ser vazio")
	private String sku;
	private String descricao;
	private Float preco;
	private Integer quantidade;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.sku = produto.getSku();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.quantidade = produto.getQuantidade();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
