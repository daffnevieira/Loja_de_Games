package com.gamestore.loja_de_games.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproduto;
	
	@NotBlank(message = "O nome não deve ser null")
	@Size(min = 5, max = 100, message = "No mínimo 5 caracteres e máximo 100")
	private String nomeProduto;
	
	@NotNull(message = "O campo quantidade não deve ser null")
	private int qtd;
	
	@NotNull(message = "O preço não deve ser null")
	private double preco;
	
	@NotBlank(message = "A descrição não deve ser null")
	@Size(min = 5, max = 100, message = "No mínimo 5 caracteres e máximo 100")
	private String descricao;

	@ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;
	
	public Long getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria getCategoria() {
	    return categoria;
	}

	public void setCategoria(Categoria categoria) {
	    this.categoria = categoria;
	}

	
	
	
	

}
