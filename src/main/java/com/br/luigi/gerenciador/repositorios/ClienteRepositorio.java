package com.br.luigi.gerenciador.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.br.luigi.gerenciador.dominio.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly = true)
	Cliente findByCpf(String cpf);

}
