package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_produtos")
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O nome é obrigatório!")
	@Size (min = 3, max = 20)
	private String nome;
	
	@NotNull(message = "É necessário informar a quantidade que deseja!")
	private Long quantidade;
	
	@NotNull(message = "O valor do produto é obrigatório!")
	private BigDecimal preco;
	
	private String foto;
	
	@NotNull(message = "Detalhar o produto é obrigatório!")
	private String detalheProduto;
	
	@NotNull(message = "Informar a dosagem é obrigatório!")
	private Long dosagem;
	
	@NotNull(message = "Informe o fabricante!")
	private String fabricante;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDetalheProduto() {
		return detalheProduto;
	}

	public void setDetalheProduto(String detalheProduto) {
		this.detalheProduto = detalheProduto;
	}

	public Long getDosagem() {
		return dosagem;
	}

	public void setDosagem(Long dosagem) {
		this.dosagem = dosagem;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
