package com.br.luigi.desafio.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.luigi.desafio.dominio.Cliente;
import com.br.luigi.desafio.servicos.ClienteServico;

@RestController
@RequestMapping(value = "/usuario")
public class ClienteRecurso {

	@Autowired
	private ClienteServico usuarioServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarUsuarioPorId(@PathVariable Integer id) {
		Cliente usuario = usuarioServico.buscarUsuarioPorId(id);
		return ResponseEntity.ok().body(usuario);
	}

}
