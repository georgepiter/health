package br.com.health.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BeneficiaryUpdateRequest {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long beneficiaryID;

	@ApiModelProperty(example = "George Piter")
	@NotNull
	private String nome;

	@ApiModelProperty(example = "11991516855")
	@NotNull
	private String telefone;

	@ApiModelProperty(example = "21-10-1988")
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Long getBeneficiaryID() {
		return beneficiaryID;
	}
}
