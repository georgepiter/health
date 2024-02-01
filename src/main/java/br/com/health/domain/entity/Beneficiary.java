package br.com.health.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "beneficiary")
public class Beneficiary implements Serializable {

	@OneToMany(mappedBy = "beneficiary", cascade = CascadeType.ALL)
	private List<Document> documents;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long beneficiaryID;

	@Column(name = "nome")
	private String name;

	@Column(name = "telefone")
	private String telephone;

	@Column(name = "data_nascimento")
	private LocalDate birthDate;

	@Column(name = "data_inclusao")
	private LocalDate inclusionDate;

	@Column(name = "data_atualizacao")
	private LocalDate updateDate;

	private Beneficiary() {
	}

	protected Beneficiary(Long beneficiaryID, String name, String telephone,
	                      LocalDate birthDate, LocalDate inclusionDate, LocalDate updateDate) {
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

		public Builder beneficiaryID(Long val) {
			beneficiaryID = val;
			return this;
		}

		public Builder name(String val) {
			name = val;
			return this;
		}

		public Builder telephone(String val) {
			telephone = val;
			return this;
		}

		public Builder birthDate(LocalDate val) {
			birthDate = val;
			return this;
		}

		public Builder inclusionDate(LocalDate val) {
			inclusionDate = val;
			return this;
		}

		public Builder updateDate(LocalDate val) {
			updateDate = val;
			return this;
		}

		public Beneficiary createNewBeneficiary() {
			return new Beneficiary(
					beneficiaryID, name, telephone,
					birthDate, inclusionDate, updateDate
			);
		}
	}

	public Long getBeneficiaryID() {
		return beneficiaryID;
	}

	public String getName() {
		return name;
	}

	public String getTelephone() {
		return telephone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public LocalDate getInclusionDate() {
		return inclusionDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setBeneficiaryID(Long beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setInclusionDate(LocalDate inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Beneficiary that = (Beneficiary) o;
		return Objects.equals(beneficiaryID, that.beneficiaryID) && Objects.equals(name, that.name)
				&& Objects.equals(telephone, that.telephone) && Objects.equals(birthDate, that.birthDate)
				&& Objects.equals(inclusionDate, that.inclusionDate) && Objects.equals(updateDate, that.updateDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(beneficiaryID, name, telephone, birthDate, inclusionDate, updateDate);
	}

	@Override
	public String toString() {
		return "Beneficiary{" +
				"beneficiaryID=" + beneficiaryID +
				", name='" + name + '\'' +
				", telephone='" + telephone + '\'' +
				", birthDate=" + birthDate +
				", inclusionDate=" + inclusionDate +
				", updateDate=" + updateDate +
				'}';
	}
}
