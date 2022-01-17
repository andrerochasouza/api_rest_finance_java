package br.com.finance.cdd.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.finance.cdd.model.entities.User;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	
	List<User> findAll();

}
