package com.gamestore.loja_de_games.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamestore.loja_de_games.model.Categoria;
import com.gamestore.loja_de_games.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByNomeProduto(String nomeProduto);
	List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
	
	Optional<Produto> findById(Long idproduto);
	List<Produto> findByCategoria(Categoria categoria);
	
}
