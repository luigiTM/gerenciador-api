package com.br.luigi.gerenciador.servicos;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.luigi.gerenciador.dominio.Cliente;
import com.br.luigi.gerenciador.dto.ClienteDTO;
import com.br.luigi.gerenciador.repositorios.ClienteRepositorio;
import com.br.luigi.gerenciador.servicos.excecoes.IntegridadeDeDadosException;
import com.br.luigi.gerenciador.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class ClienteServico {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");

	public Cliente inserir(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	public void deletar(Integer id) {
		buscarPorId(id);
		try {
			clienteRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException(Cliente.class.getSimpleName());
		}
	}

	public Cliente atualizarCliente(Cliente cliente) {
		Cliente novoCliente = buscarPorId(cliente.getId());
		atualizaInformacoesCliente(cliente, novoCliente);
		return clienteRepositorio.save(novoCliente);
	}

	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = clienteRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Cliente.class.getSimpleName()));
	}

	public List<Cliente> buscarTodos() {
		return clienteRepositorio.findAll();
	}

	public Page<Cliente> buscarClientesPaginado(Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		PageRequest requisicaoDePagina = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		return clienteRepositorio.findAll(requisicaoDePagina);
	}

	public Cliente deUmDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getNome(), clienteDto.getCpf(), clienteDto.getDataNascimento());
	}

	private void atualizaInformacoesCliente(Cliente cliente, Cliente novoCliente) {
		novoCliente.setNome((cliente.getNome() == null) ? novoCliente.getNome() : cliente.getNome());
		novoCliente.setCpf((cliente.getCpf() == null) ? novoCliente.getCpf() : cliente.getCpf());
		novoCliente.setDataNascimento((cliente.getDataNascimento() == null) ? novoCliente.getDataNascimento() : cliente.getDataNascimento());
	}

}
