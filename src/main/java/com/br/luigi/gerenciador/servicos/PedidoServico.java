package com.br.luigi.gerenciador.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.luigi.gerenciador.dominio.Pedido;
import com.br.luigi.gerenciador.dto.PedidoDTO;
import com.br.luigi.gerenciador.repositorios.PedidoRepositorio;
import com.br.luigi.gerenciador.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class PedidoServico {

	@Autowired
	private PedidoRepositorio pedidoRepositorio;

	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> obj = pedidoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Pedido.class.getSimpleName()));
	}

	public Pedido inserir(Pedido pedido) {
		return pedidoRepositorio.save(pedido);
	}

	public Pedido atualizarPedido(Pedido pedido) {
		Pedido novoPedido = buscarPorId(pedido.getId());
		atualizaInformacoesPedido(pedido, novoPedido);
		return pedidoRepositorio.save(novoPedido);
	}

	public void deletar(Integer id) {
		buscarPorId(id);
		pedidoRepositorio.deleteById(id);
	}

	public Pedido deUmDTO(PedidoDTO pedidoDto) {
		return new Pedido(pedidoDto.getCliente(), pedidoDto.getTotalCompra(), pedidoDto.getDataCompra());
	}

	public List<Pedido> buscarTodos() {
		return pedidoRepositorio.findAll();
	}

	public Page<Pedido> buscarPedidosPaginado(Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		PageRequest requisicaoDePagina = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		return pedidoRepositorio.findAll(requisicaoDePagina);
	}

	private void atualizaInformacoesPedido(Pedido pedido, Pedido novoPedido) {
		novoPedido.setCliente((pedido.getCliente() == null) ? novoPedido.getCliente() : pedido.getCliente());
		novoPedido.setTotalCompra((pedido.getTotalCompra() == null) ? novoPedido.getTotalCompra() : pedido.getTotalCompra());
	}

}
