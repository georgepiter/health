package br.com.health.domain.repository;

import br.com.health.domain.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	Optional<Beneficiary> findByBeneficiaryID(Long beneficiaryID);
}
