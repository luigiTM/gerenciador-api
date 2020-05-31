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

import com.br.luigi.gerenciador.dominio.Pedido;
import com.br.luigi.gerenciador.dto.PedidoDTO;
import com.br.luigi.gerenciador.servicos.PedidoServico;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoRecurso {

	@Autowired
	private PedidoServico pedidoServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PedidoDTO> buscarpedidoPorId(@PathVariable Integer id) {
		Pedido pedido = pedidoServico.buscarPorId(id);
		return ResponseEntity.ok().body(new PedidoDTO(pedido));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> buscarTodos() {
		List<Pedido> pedidos = pedidoServico.buscarTodos();
		List<PedidoDTO> pedidosDTO = pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
		return ResponseEntity.ok().body(pedidosDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody PedidoDTO pedidoDto) {
		Pedido pedido = pedidoServico.deUmDTO(pedidoDto);
		pedido = pedidoServico.inserir(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarPedido(@Valid @RequestBody PedidoDTO edidoDTO, @PathVariable Integer id) {
		Pedido pedido = pedidoServico.deUmDTO(edidoDTO);
		pedido.setId(id);
		pedido = pedidoServico.atualizarPedido(pedido);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		pedidoServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
