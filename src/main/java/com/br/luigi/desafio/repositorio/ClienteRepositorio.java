package com.br.luigi.desafio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.luigi.desafio.dominio.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

}
