package br.com.finance.cdd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
