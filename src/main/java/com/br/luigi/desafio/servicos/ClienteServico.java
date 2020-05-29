package com.br.luigi.desafio.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.luigi.desafio.dominio.Cliente;
import com.br.luigi.desafio.repositorio.ClienteRepositorio;
import com.br.luigi.desafio.servicos.excecoes.ObjetoNaoEncontradoException;

@RestController
@RequestMapping(value = "/usuario")
public class ClienteServico {

	@Autowired
	private ClienteRepositorio usuarioRepositorio;

	public Cliente buscarUsuarioPorId(Integer id) {
		Optional<Cliente> obj = usuarioRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Cliente.class.getSimpleName()));
	}

}
