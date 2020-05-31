package com.br.luigi.gerenciador.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.luigi.gerenciador.dominio.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	Produto findBySku(String sku);

}
