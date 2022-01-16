package br.com.finance.cdd.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.finance.cdd.model.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	
	List<User> findAll();

}
