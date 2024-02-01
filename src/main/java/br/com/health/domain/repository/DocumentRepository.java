package br.com.health.domain.repository;

import br.com.health.domain.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Optional<Document> findByDocumentID(Long documentID);

	@Query("SELECT d FROM Document d WHERE d.beneficiary.beneficiaryID = :beneficiaryID")
	List<Document> findByBeneficiaryId(@Param("beneficiaryID") Long beneficiaryID);
}

