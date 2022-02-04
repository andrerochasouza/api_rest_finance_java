package br.com.finance.cdd.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.finance.cdd.model.Aplication;

@Repository
public interface AplicationRepository extends JpaRepository<Aplication, Long> {

	@Query(value = "SELECT * FROM FINANCE_IO.APLICATIONS u " +
                    "WHERE u.id_wallet_aplication = ?1" +
                    " && u.date_delete is null",
            nativeQuery = true)
    List<Aplication> findByAplicationsPage(Long id_wallet_aplication, Pageable pageable);
}
