package com.gamestore.loja_de_games.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamestore.loja_de_games.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findByNomeCategoria(String nomeCategoria);
	List<Categoria> findByNomeCategoriaContainingIgnoreCase(String nomeCategoria);

	Optional<Categoria> findById(Long idproduto);
}
