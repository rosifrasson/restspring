package br.rosi.com.restspring.bankslip;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, UUID>{

}