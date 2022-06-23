package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/precoMaior/{preco}")
	public ResponseEntity<List<Produto>> getByMaiorPreco(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThan(preco));
	}
	
	@GetMapping("/precoMenor/{preco}")
	public ResponseEntity<List<Produto>> getByMenorPreco(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThan(preco));
	}
	
	@GetMapping("/between/{valor1}/{valor2}")
	public ResponseEntity<List<Produto>> getByPrecoBetween(@PathVariable BigDecimal valor1, @PathVariable BigDecimal valor2){
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(valor1, valor2));
	}
	
	
	@GetMapping("/and/{nome}/{fabricante}")
	public ResponseEntity<List<Produto>> getByNomeAndFabricante(@PathVariable String nome, @PathVariable String fabricante){
		return ResponseEntity.ok(produtoRepository.findByNomeAndFabricante(nome, fabricante));
	}
	
	@GetMapping("/or/{nome}/{fabricante}")
	public ResponseEntity<List<Produto>> getByNomeOrFabricante(@PathVariable String nome, @PathVariable String fabricante) {
		return ResponseEntity.ok(produtoRepository.findByNomeOrFabricante(nome, fabricante));
	}
	
	@GetMapping("/containing/{nome}")
	public ResponseEntity<List<Produto>> getByNomeContaining(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContaining(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto){
		
	if(categoriaRepository.existsById(produto.getCategoria().getId())) 
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable Long id) {
		Optional <Produto> resposta = produtoRepository.findById(id);
		if (resposta.isPresent()) {
			produtoRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
}

