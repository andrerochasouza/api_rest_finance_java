package br.com.finance.cdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long>{

}
