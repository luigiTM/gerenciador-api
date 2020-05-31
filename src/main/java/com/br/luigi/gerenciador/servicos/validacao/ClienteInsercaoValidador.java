package com.br.luigi.gerenciador.servicos.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.br.luigi.gerenciador.dominio.Cliente;
import com.br.luigi.gerenciador.dto.ClienteDTO;
import com.br.luigi.gerenciador.recursos.excecoes.MensagemCampos;
import com.br.luigi.gerenciador.repositorios.ClienteRepositorio;

public class ClienteInsercaoValidador implements ConstraintValidator<ClienteInsercao, ClienteDTO> {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	@Autowired
	HttpServletRequest requisicao;

	@Override
	public void initialize(ClienteInsercao clienteInsercao) {
	}

	@Override
	public boolean isValid(ClienteDTO clienteDto, ConstraintValidatorContext contexto) {

		List<MensagemCampos> lista = new ArrayList<>();
		if (requisicao.getMethod().equals("POST")) {
			Cliente clienteExistente = clienteRepositorio.findByCpf(clienteDto.getCpf());
			if (clienteExistente != null) {
				lista.add(new MensagemCampos("cpf", "CPF já existente"));
			}
		}
		if (requisicao.getMethod().equals("PUT")) {
			Map<String, String> mapaRequisicao = (Map<String, String>) requisicao.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			String uriCpf = (mapaRequisicao.get("cpf") == null ? "0" : mapaRequisicao.get("cpf"));
			Cliente clienteExistente = clienteRepositorio.findByCpf(clienteDto.getCpf());
			if (clienteExistente != null && !clienteExistente.getCpf().equals(uriCpf)) {
				lista.add(new MensagemCampos("cpf", "CPF já existente"));
			}
		}

		for (MensagemCampos mensagem : lista) {
			contexto.disableDefaultConstraintViolation();
			contexto.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}

}
