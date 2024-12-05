package com.gamestore.loja_de_games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamestore.loja_de_games.model.Categoria;
import com.gamestore.loja_de_games.model.Produto;
import com.gamestore.loja_de_games.repository.CategoriaRepository;
import com.gamestore.loja_de_games.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;


	@GetMapping("/todos")
	public ResponseEntity<List<Produto>> getAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{idproduto}")
	public ResponseEntity<Produto> getById(@PathVariable Long idproduto) {
		Optional<Produto> produto = produtoRepository.findById(idproduto);

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/nome/{nomeProduto}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nomeProduto) {
		List<Produto> produto = produtoRepository.findByNomeProdutoContainingIgnoreCase(nomeProduto);
		if (!produto.isEmpty()) {
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
	    if (produto.getCategoria() != null && produto.getCategoria().getIdcategoria() != null) {
	        Optional<Categoria> categoria = categoriaRepository.findById(produto.getCategoria().getIdcategoria());
	        if (categoria.isPresent()) {
	            produto.setCategoria(categoria.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }
	    Produto novoProduto = produtoRepository.save(produto);
	    return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	@PutMapping("/{idproduto}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long idproduto, @RequestBody Produto produtoSet) {
		Optional<Produto> produtoExistente = produtoRepository.findById(idproduto);
		if (produtoExistente.isPresent()) {
			Produto produto = produtoExistente.get();
			produto.setNomeProduto(produtoSet.getNomeProduto());
			produto.setQtd(produtoSet.getQtd());
			produto.setPreco(produtoSet.getPreco());
			produto.setDescricao(produtoSet.getDescricao());

			produtoRepository.save(produto);
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{idproduto}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Long idproduto) {
		Optional<Produto> produto = produtoRepository.findById(idproduto);
		if (produto.isPresent()) {
			produtoRepository.deleteById(idproduto);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
