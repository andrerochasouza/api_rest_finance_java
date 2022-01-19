package br.com.finance.cdd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finance.cdd.model.entities.Pay;

public interface PayRepository extends JpaRepository<Pay, Long>{

}
