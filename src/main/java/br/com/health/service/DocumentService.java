package br.com.health.service;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.DocumentRequest;
import br.com.health.domain.dto.response.DocumentResponse;
import br.com.health.domain.entity.Beneficiary;
import br.com.health.domain.entity.Document;
import br.com.health.domain.exception.BeneficiaryNotFoundException;
import br.com.health.domain.exception.DataBaseException;
import br.com.health.domain.exception.DocumentNotFoundException;
import br.com.health.domain.exception.FieldException;
import br.com.health.domain.repository.BeneficiaryRepository;
import br.com.health.domain.repository.DocumentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

	private final DocumentRepository documentRepository;
	private final BeneficiaryRepository beneficiaryRepository;

	public DocumentService(DocumentRepository documentRepository,
	                       BeneficiaryRepository beneficiaryRepository) {
		this.documentRepository = documentRepository;
		this.beneficiaryRepository = beneficiaryRepository;
	}

	public ResponseEntityCustom postDocument(DocumentRequest documentRequest) throws FieldException, DataBaseException, BeneficiaryNotFoundException {
		documentRequest.validFields(documentRequest);

		Beneficiary beneficiary = beneficiaryRepository.findByBeneficiaryID(documentRequest.getBeneficiaryID())
				.orElseThrow(() -> new BeneficiaryNotFoundException("Beneficiário não encontrado"));
		try {
			Document newDocument = new Document.Builder()
					.attachedDocument(documentRequest.getDocumentByte())
					.documentType(documentRequest.getTypeDocument())
					.description(documentRequest.getDescription())
					.inclusionDate(LocalDate.now())
					.beneficiary(beneficiary)
					.createNewDocument();
			documentRepository.save(newDocument);
		} catch (Exception e) {
			throw new DataBaseException("Erro ao persistir o documento no banco de dados", e);
		}

		return new ResponseEntityCustom(HttpStatus.CREATED.value(), HttpStatus.CREATED, "Documento anexado ao beneficiario com sucesso!");
	}

	public List<DocumentResponse> getAllDocumentsByBeneficiaryID(Long beneficiaryID) {
		List<Document> documents = documentRepository.findByBeneficiaryId(beneficiaryID);
		List<DocumentResponse> documentsResponse = new ArrayList<>();

		documents.forEach(document -> {
			DocumentResponse documentResponse = new DocumentResponse.Builder()
					.attachedDocument(document.getAttachedDocument())
					.updateDate(document.getUpdateDate())
					.documentID(document.getDocumentID())
					.documentType(document.getDocumentType())
					.inclusionDate(document.getInclusionDate())
					.createDocumentResponse();
			documentsResponse.add(documentResponse);
		});

		return documentsResponse;
	}

	public ResponseEntityCustom deleteDocumentByDocumentID(Long documentID) throws DocumentNotFoundException {
		Document document = getDocumentByID(documentID);
		documentRepository.delete(document);
		return new ResponseEntityCustom(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, "Documento deletado com sucesso!");
	}

	private Document getDocumentByID(Long documentID) throws DocumentNotFoundException {
		return documentRepository.findByDocumentID(documentID).orElseThrow(() -> new DocumentNotFoundException("Documento não encontrado pelo ID"));
	}
}
