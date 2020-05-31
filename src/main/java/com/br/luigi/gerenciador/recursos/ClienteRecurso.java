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

import com.br.luigi.gerenciador.dominio.Cliente;
import com.br.luigi.gerenciador.dto.ClienteDTO;
import com.br.luigi.gerenciador.servicos.ClienteServico;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteRecurso {

	@Autowired
	private ClienteServico clienteServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Integer id) {
		Cliente cliente = clienteServico.buscarPorId(id);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<Cliente> clientes = clienteServico.buscarTodos();
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientesDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteDTO clienteDto) {
		Cliente cliente = clienteServico.deUmDTO(clienteDto);
		cliente = clienteServico.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
		Cliente cliente = clienteServico.deUmDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteServico.atualizarCliente(cliente);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
		clienteServico.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
