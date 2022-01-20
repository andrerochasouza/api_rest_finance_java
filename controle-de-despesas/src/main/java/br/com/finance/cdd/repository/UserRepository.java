package br.com.finance.cdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
