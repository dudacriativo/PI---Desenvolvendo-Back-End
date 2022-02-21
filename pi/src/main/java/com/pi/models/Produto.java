package com.pi.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String qtd_produto;
	
	@NotEmpty
	private String valor;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQtd_produto() {
		return qtd_produto;
	}

	public void setQtd_produto(String qtd_produto) {
		this.qtd_produto = qtd_produto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
