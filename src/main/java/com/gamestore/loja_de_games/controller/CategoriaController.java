package com.gamestore.loja_de_games.controller;

import java.util.List;
import com.gamestore.loja_de_games.model.Categoria;
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

import com.gamestore.loja_de_games.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/todas")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
    @GetMapping("/nome/{nomeCategoria}")
    public ResponseEntity<List<Categoria>> getCategoriaByName(@PathVariable String nomeCategoria) {
        List<Categoria> categorias = categoriaRepository.findByNomeCategoriaContainingIgnoreCase(nomeCategoria);
        if (categorias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categorias);
        }
    }

    @GetMapping("/{idcategoria}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long idcategoria) {
        return categoriaRepository.findById(idcategoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping("/{idcategoria}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long idcategoria, @RequestBody Categoria categoria) {
        return categoriaRepository.findById(idcategoria).map(existingCategoria -> {
            existingCategoria.setNomeCategoria(categoria.getNomeCategoria());
            existingCategoria.setDescricao(categoria.getDescricao());
            return ResponseEntity.ok(categoriaRepository.save(existingCategoria));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idcategoria}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long idcategoria) {
        if (categoriaRepository.existsById(idcategoria)) {
        	
            categoriaRepository.deleteById(idcategoria);
            return ResponseEntity.noContent().build();
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
