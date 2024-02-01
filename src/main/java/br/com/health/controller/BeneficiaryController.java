package br.com.health.controller;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.BeneficiaryRequest;
import br.com.health.domain.dto.request.BeneficiaryUpdateRequest;
import br.com.health.domain.dto.response.BeneficiaryResponse;
import br.com.health.domain.exception.BeneficiaryNotFoundException;
import br.com.health.domain.exception.DataBaseException;
import br.com.health.service.BeneficiaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "3.Beneficiario", description = "Controlador responsável por gerenciar os beneficiários")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"3.Beneficiario"})
@RequestMapping("api/v1/beneficiary")
public class BeneficiaryController {

	private final BeneficiaryService beneficiaryService;

	public BeneficiaryController(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@PostMapping(value = "/create")
	@ApiOperation(value = "Método que cadastra um beneficiario e seus documentos")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom createNewBeneficiary(@RequestBody BeneficiaryRequest request) throws DataBaseException {
		return beneficiaryService.createNewBeneficiary(request);
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "Método retorna todos beneficiarios cadastrados")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<BeneficiaryResponse> getAllBeneficiaries() {
		return beneficiaryService.getAllBeneficiaries();
	}

	@PutMapping(value = "/update")
	@ApiOperation(value = "Método que atualiza os dados cadastrais de um beneficiário por ID")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public BeneficiaryResponse updateBeneficiaryByID(@RequestBody BeneficiaryUpdateRequest request) throws BeneficiaryNotFoundException, DataBaseException {
		return beneficiaryService.updateBeneficiary(request);
	}

	@DeleteMapping(value = "/delete/{beneficiaryID}")
	@ApiOperation(value = "Método que deleta o beneficiário e seus documentos")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom deleteBeneficiaryByID(@PathVariable Long beneficiaryID) throws BeneficiaryNotFoundException, DataBaseException {
		return beneficiaryService.deleteBeneficiaryByID(beneficiaryID);
	}
}


