package br.com.health.controller;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.DocumentRequest;
import br.com.health.domain.dto.response.DocumentResponse;
import br.com.health.domain.exception.BeneficiaryNotFoundException;
import br.com.health.domain.exception.DataBaseException;
import br.com.health.domain.exception.DocumentNotFoundException;
import br.com.health.domain.exception.FieldException;
import br.com.health.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "4.Documento", description = "Controlador responsável por gerenciar os documentos dos beneficiários")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"4.Documento"})
@RequestMapping("api/v1/document")
public class DocumentController {

	private final DocumentService documentService;

	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	@PostMapping(value = "/post-document")
	@ApiOperation(value = "Método que inclui um documento para beneficiaryID")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom postNewDocument(@RequestBody DocumentRequest documentRequest) throws FieldException, DataBaseException, BeneficiaryNotFoundException {
		return documentService.postDocument(documentRequest);
	}

	@GetMapping(value = "/all/{beneficiaryID}")
	@ApiOperation(value = "Obtém todos documentos do beneficiario pelo beneficiaryID")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
	public List<DocumentResponse> getAllDocumentsByBeneficiaryID(@PathVariable Long beneficiaryID) {
		return documentService.getAllDocumentsByBeneficiaryID(beneficiaryID);
	}

	@DeleteMapping(value = "/{documentID}")
	@ApiOperation(value = "Método que deleta o documento pelo documentID")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom deleteDocumentByDocumentID(@PathVariable Long documentID) throws UsernameNotFoundException, DocumentNotFoundException {
		return documentService.deleteDocumentByDocumentID(documentID);
	}
}
