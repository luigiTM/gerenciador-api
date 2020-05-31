package com.br.luigi.gerenciador.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.luigi.gerenciador.dominio.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

}
