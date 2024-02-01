package br.com.health.service;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.BeneficiaryRequest;
import br.com.health.domain.dto.request.BeneficiaryUpdateRequest;
import br.com.health.domain.dto.request.DocumentRequest;
import br.com.health.domain.dto.response.BeneficiaryResponse;
import br.com.health.domain.entity.Beneficiary;
import br.com.health.domain.entity.Document;
import br.com.health.domain.exception.BeneficiaryNotFoundException;
import br.com.health.domain.exception.DataBaseException;
import br.com.health.domain.repository.BeneficiaryRepository;
import br.com.health.domain.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeneficiaryService {

	private final Logger logger = LoggerFactory.getLogger(BeneficiaryService.class);

	private final DocumentRepository documentRepository;
	private final BeneficiaryRepository beneficiaryRepository;

	public BeneficiaryService(DocumentRepository documentRepository,
	                          BeneficiaryRepository beneficiaryRepository) {
		this.documentRepository = documentRepository;
		this.beneficiaryRepository = beneficiaryRepository;
	}

	public ResponseEntityCustom createNewBeneficiary(BeneficiaryRequest beneficiaryRequest) throws DataBaseException {
		try {
			Beneficiary newBeneficiary = new Beneficiary.Builder()
					.inclusionDate(LocalDate.now())
					.name(beneficiaryRequest.getNome())
					.birthDate(beneficiaryRequest.getDataNascimento())
					.telephone(beneficiaryRequest.getTelefone())
					.createNewBeneficiary();

			Beneficiary beneficiarySaved = beneficiaryRepository.save(newBeneficiary);

			saveDocuments(beneficiaryRequest, beneficiarySaved);
		} catch (Exception e) {
			throw new DataBaseException("Erro ao salvar beneficiario");
		}

		return new ResponseEntityCustom(HttpStatus.CREATED.value(), HttpStatus.CREATED, "Beneficiao criado com sucesso!");
	}

	private void saveDocuments(BeneficiaryRequest beneficiaryRequest, Beneficiary beneficiarySaved) {

		for (DocumentRequest document : beneficiaryRequest.getDocuments()) {
			try {
				Document newDocument = new Document.Builder()
						.attachedDocument(document.getDocumentByte())
						.documentType(document.getTypeDocument())
						.description(document.getDescription())
						.inclusionDate(LocalDate.now())
						.beneficiary(beneficiarySaved)
						.createNewDocument();

				documentRepository.save(newDocument);
			} catch (Exception e) {
				logger.error("Erro ao salvar documento, documentType='{}'", document.getTypeDocument(), e);
			}
		}
	}

	public List<BeneficiaryResponse> getAllBeneficiaries() {
		List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
		List<BeneficiaryResponse> beneficiariesResponse = new ArrayList<>();

		beneficiaries.forEach(beneficiary -> {
			BeneficiaryResponse beneficiaryResponse = new BeneficiaryResponse.Builder()
					.beneficiaryID(beneficiary.getBeneficiaryID())
					.birthDate(beneficiary.getBirthDate())
					.inclusionDate(beneficiary.getInclusionDate())
					.name(beneficiary.getName())
					.updateDate(beneficiary.getUpdateDate())
					.telephone(beneficiary.getTelephone())
					.inclusionDate(beneficiary.getInclusionDate())
					.createBeneficiaryResponse();
			beneficiariesResponse.add(beneficiaryResponse);
		});

		return beneficiariesResponse;
	}

	public BeneficiaryResponse updateBeneficiary(BeneficiaryUpdateRequest request) throws BeneficiaryNotFoundException, DataBaseException {

		Beneficiary beneficiary = beneficiaryRepository.findById(request.getBeneficiaryID())
				.orElseThrow(() -> new BeneficiaryNotFoundException("Beneficiário não encontrado pelo ID"));

		try {
			beneficiary.setBeneficiaryID(request.getBeneficiaryID());
			beneficiary.setBirthDate(request.getDataNascimento());
			beneficiary.setName(request.getNome());
			beneficiary.setTelephone(request.getTelefone());
			beneficiary.setUpdateDate(LocalDate.now());

			Beneficiary beneficiarySaved = beneficiaryRepository.save(beneficiary);

			return new BeneficiaryResponse.Builder()
					.name(beneficiarySaved.getName())
					.birthDate(beneficiarySaved.getBirthDate())
					.telephone(beneficiarySaved.getTelephone())
					.beneficiaryID(beneficiarySaved.getBeneficiaryID())
					.inclusionDate(beneficiarySaved.getInclusionDate())
					.updateDate(beneficiarySaved.getUpdateDate())
					.createBeneficiaryResponse();

		} catch (Exception e) {
			throw new DataBaseException("Beneficiário não salvo no banco de dados", e);
		}
	}

	public ResponseEntityCustom deleteBeneficiaryByID(Long beneficiaryID) throws BeneficiaryNotFoundException, DataBaseException {
		Beneficiary beneficiary = beneficiaryRepository.findByBeneficiaryID(beneficiaryID)
				.orElseThrow(() -> new BeneficiaryNotFoundException("Beneficiário não encontrado pelo ID"));
		try {
			beneficiaryRepository.delete(beneficiary);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Erro ao deletar beneficiário do banco", e);
		}
		return new ResponseEntityCustom(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, "Beneficiário deletado com sucesso!");
	}
}
