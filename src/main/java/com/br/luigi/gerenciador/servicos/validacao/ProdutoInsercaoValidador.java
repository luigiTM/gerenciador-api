package com.br.luigi.gerenciador.servicos.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.br.luigi.gerenciador.dominio.Produto;
import com.br.luigi.gerenciador.dto.ProdutoDTO;
import com.br.luigi.gerenciador.recursos.excecoes.MensagemCampos;
import com.br.luigi.gerenciador.repositorios.ProdutoRepositorio;

public class ProdutoInsercaoValidador implements ConstraintValidator<ProdutoInsercao, ProdutoDTO> {

	@Autowired
	ProdutoRepositorio produtoRepositorio;
	@Autowired
	HttpServletRequest requisicao;

	@Override
	public void initialize(ProdutoInsercao produtoInsercao) {
	}

	@Override
	public boolean isValid(ProdutoDTO produtoDto, ConstraintValidatorContext contexto) {

		List<MensagemCampos> lista = new ArrayList<>();
		if (requisicao.getMethod().equals("POST")) {
			Produto produtoExistente = produtoRepositorio.findBySku(produtoDto.getSku());
			if (produtoExistente != null) {
				lista.add(new MensagemCampos("sku", "SKU já existente"));
			}
		}
		if (requisicao.getMethod().equals("PUT")) {
			Map<String, String> mapaRequisicao = (Map<String, String>) requisicao.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			String uriSku = (mapaRequisicao.get("id") == null ? "0" : mapaRequisicao.get("id"));
			Produto produtoExistente = produtoRepositorio.findBySku(produtoDto.getSku());
			if (produtoExistente != null && !produtoExistente.getSku().equals(uriSku)) {
				lista.add(new MensagemCampos("sku", "SKU já existente"));
			}
		}

		for (MensagemCampos mensagem : lista) {
			contexto.disableDefaultConstraintViolation();
			contexto.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}

}
