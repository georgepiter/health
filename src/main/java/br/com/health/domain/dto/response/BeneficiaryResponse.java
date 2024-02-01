package br.com.health.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryResponse {

	@JsonFormat(pattern = "ID")
	private final Long beneficiaryID;

	@JsonFormat(pattern = "name")
	private final String name;

	@JsonFormat(pattern = "telephone")
	private final String telephone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private final LocalDate birthDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private final LocalDate inclusionDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private final LocalDate updateDate;

	protected BeneficiaryResponse(Long beneficiaryID, String name,
	                              String telephone, LocalDate birthDate,
	                              LocalDate inclusionDate, LocalDate updateDate) {
		this.beneficiaryID = beneficiaryID;
		this.name = name;
		this.telephone = telephone;
		this.birthDate = birthDate;
		this.inclusionDate = inclusionDate;
		this.updateDate = updateDate;
	}

	public static final class Builder {
		private Long beneficiaryID;
		private String name;
		private String telephone;
		private LocalDate birthDate;
		private LocalDate inclusionDate;
		private LocalDate updateDate;

		public Builder() {
		}

		public BeneficiaryResponse.Builder beneficiaryID(Long val) {
			beneficiaryID = val;
			return this;
		}

		public BeneficiaryResponse.Builder name(String val) {
			name = val;
			return this;
		}

		public BeneficiaryResponse.Builder telephone(String val) {
			telephone = val;
			return this;
		}

		public BeneficiaryResponse.Builder birthDate(LocalDate val) {
			birthDate = val;
			return this;
		}

		public BeneficiaryResponse.Builder inclusionDate(LocalDate val) {
			inclusionDate = val;
			return this;
		}

		public BeneficiaryResponse.Builder updateDate(LocalDate val) {
			updateDate = val;
			return this;
		}

		public BeneficiaryResponse createBeneficiaryResponse() {
			return new BeneficiaryResponse(
					beneficiaryID, name, telephone,
					birthDate, inclusionDate, updateDate
			);
		}
	}
}
