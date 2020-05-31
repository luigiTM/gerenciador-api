package com.br.luigi.gerenciador.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.luigi.gerenciador.dominio.Produto;
import com.br.luigi.gerenciador.dto.ProdutoDTO;
import com.br.luigi.gerenciador.servicos.ProdutoServico;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRecurso {

	@Autowired
	private ProdutoServico produtoServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProdutoDTO> buscarprodutoPorId(@PathVariable Integer id) {
		Produto produto = produtoServico.buscarPorId(id);
		return ResponseEntity.ok().body(new ProdutoDTO(produto));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> buscarTodos() {
		List<Produto> produtos = produtoServico.buscarTodos();
		List<ProdutoDTO> produtosDTO = produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(produtosDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody ProdutoDTO produtoDto) {
		Produto produto = produtoServico.deUmDTO(produtoDto);
		produto = produtoServico.inserir(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarProduto(@Valid @RequestBody ProdutoDTO pedidoDTO, @PathVariable Integer id) {
		Produto produto = produtoServico.deUmDTO(pedidoDTO);
		produto.setId(id);
		produto = produtoServico.atualizarProduto(produto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		produtoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
