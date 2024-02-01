package br.com.health.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "document")
public class Document implements Serializable {

	@ManyToOne
	@JoinColumn(name = "beneficiary_id")
	private Beneficiary beneficiary;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long documentID;

	@Column(name = "tipo_documento")
	private String documentType;

	@Column(name = "descricao")
	private String description;

	@Column(name = "data_inclusao")
	private LocalDate inclusionDate;

	@Column(name = "data_atualizacao")
	private LocalDate updateDate;

	@Lob
	@Column(name = "attached_document", length = 16777215)
	private byte[] attachedDocument;

	private Document() {
	}

	protected Document(Long documentID, String documentType,
	                   String description, LocalDate inclusionDate,
	                   LocalDate updateDate, byte[] attachedDocument, Beneficiary beneficiary) {
		this.documentID = documentID;
		this.documentType = documentType;
		this.description = description;
		this.inclusionDate = inclusionDate;
		this.updateDate = updateDate;
		this.attachedDocument = attachedDocument;
		this.beneficiary = beneficiary;
	}

	public static final class Builder {
		private Long documentID;
		private String documentType;
		private String description;
		private LocalDate inclusionDate;
		private LocalDate updateDate;
		private byte[] attachedDocument;
		private Beneficiary beneficiary;

		public Builder() {
		}

		public Builder documentID(Long val) {
			documentID = val;
			return this;
		}

		public Builder documentType(String val) {
			documentType = val;
			return this;
		}

		public Builder description(String val) {
			description = val;
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

		public Builder attachedDocument(byte[] val) {
			attachedDocument = val;
			return this;
		}

		public Builder beneficiary(Beneficiary val) {
			beneficiary = val;
			return this;
		}

		public Document createNewDocument() {
			return new Document(
					documentID, documentType,
					description, inclusionDate,
					updateDate, attachedDocument, beneficiary
			);
		}
	}

	public Long getDocumentID() {
		return documentID;
	}

	public String getDocumentType() {
		return documentType;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getInclusionDate() {
		return inclusionDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public byte[] getAttachedDocument() {
		return attachedDocument;
	}

	public Long getBeneficiaryId() {
		return beneficiary != null ? beneficiary.getBeneficiaryID() : null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Document document = (Document) o;
		return Objects.equals(documentID, document.documentID)
				&& Objects.equals(documentType, document.documentType)
				&& Objects.equals(description, document.description)
				&& Objects.equals(inclusionDate, document.inclusionDate)
				&& Objects.equals(updateDate, document.updateDate)
				&& Arrays.equals(attachedDocument, document.attachedDocument);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(documentID, documentType, description, inclusionDate, updateDate);
		result = 31 * result + Arrays.hashCode(attachedDocument);
		return result;
	}

	@Override
	public String toString() {
		return "Document{" +
				"documentID=" + documentID +
				", documentType='" + documentType + '\'' +
				", description='" + description + '\'' +
				", inclusionDate=" + inclusionDate +
				", updateDate=" + updateDate +
				'}';
	}
}
