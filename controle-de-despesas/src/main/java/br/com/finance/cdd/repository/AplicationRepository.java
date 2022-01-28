package br.com.finance.cdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finance.cdd.model.Aplication;

public interface AplicationRepository extends JpaRepository<Aplication, Long> {

}
