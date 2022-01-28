package br.com.finance.cdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finance.cdd.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
