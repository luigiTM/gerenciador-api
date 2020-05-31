package com.br.luigi.gerenciador.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.luigi.gerenciador.dominio.Produto;
import com.br.luigi.gerenciador.dto.ProdutoDTO;
import com.br.luigi.gerenciador.repositorios.ProdutoRepositorio;
import com.br.luigi.gerenciador.servicos.excecoes.IntegridadeDeDadosException;
import com.br.luigi.gerenciador.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class ProdutoServico {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	public Produto inserir(Produto produto) {
		return produtoRepositorio.save(produto);
	}

	public void deletar(Integer id) {
		buscarPorId(id);
		try {
			produtoRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException(Produto.class.getSimpleName());
		}
	}

	public Produto atualizarProduto(Produto produto) {
		Produto novoProduto = buscarPorId(produto.getId());
		atualizaInformacoesProduto(produto, novoProduto);
		return produtoRepositorio.save(novoProduto);
	}

	public Produto buscarPorId(Integer id) {
		Optional<Produto> obj = produtoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Produto.class.getSimpleName()));
	}

	public List<Produto> buscarTodos() {
		return produtoRepositorio.findAll();
	}

	public Page<Produto> buscarProdutosPaginado(Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		PageRequest requisicaoDePagina = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		return produtoRepositorio.findAll(requisicaoDePagina);
	}

	public Produto deUmDTO(ProdutoDTO produtoDto) {
		return new Produto(produtoDto.getSku(), produtoDto.getNome(), produtoDto.getDescricao(), produtoDto.getPreco(), produtoDto.getQuantidade());
	}

	private void atualizaInformacoesProduto(Produto produto, Produto novoProduto) {
		novoProduto.setSku((produto.getSku() == null) ? novoProduto.getSku() : produto.getSku());
		novoProduto.setNome((produto.getNome() == null) ? novoProduto.getNome() : produto.getNome());
		novoProduto.setDescricao((produto.getDescricao() == null) ? novoProduto.getDescricao() : produto.getDescricao());
		novoProduto.setPreco((produto.getPreco() == 0) ? novoProduto.getPreco() : produto.getPreco());
		novoProduto.setQuantidade((produto.getQuantidade() == 0) ? novoProduto.getQuantidade() : produto.getQuantidade());
	}

}
