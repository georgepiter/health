package br.com.health.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentResponse {

	@JsonFormat(pattern = "ID")
	private final Long documentID;

	@JsonFormat(pattern = "documentType")
	private final String documentType;

	@JsonFormat(pattern = "description")
	private final String description;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate inclusionDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate updateDate;

	@JsonFormat(pattern = "attachedDocument")
	private final byte[] attachedDocument;

	protected DocumentResponse(Long documentID, String documentType,
	                           String description, LocalDate inclusionDate,
	                           LocalDate updateDate, byte[] attachedDocument) {
		this.documentID = documentID;
		this.documentType = documentType;
		this.description = description;
		this.inclusionDate = inclusionDate;
		this.updateDate = updateDate;
		this.attachedDocument = attachedDocument;
	}

	public static final class Builder {
		private Long documentID;
		private String documentType;
		private String description;
		private LocalDate inclusionDate;
		private LocalDate updateDate;
		private byte[] attachedDocument;

		public Builder() {
		}

		public DocumentResponse.Builder documentID(Long val) {
			documentID = val;
			return this;
		}

		public DocumentResponse.Builder documentType(String val) {
			documentType = val;
			return this;
		}

		public DocumentResponse.Builder description(String val) {
			description = val;
			return this;
		}

		public DocumentResponse.Builder inclusionDate(LocalDate val) {
			inclusionDate = val;
			return this;
		}

		public DocumentResponse.Builder updateDate(LocalDate val) {
			updateDate = val;
			return this;
		}

		public DocumentResponse.Builder attachedDocument(byte[] val) {
			attachedDocument = val;
			return this;
		}

		public DocumentResponse createDocumentResponse() {
			return new DocumentResponse(
					documentID, documentType,
					description, inclusionDate,
					updateDate, attachedDocument
			);
		}
	}

}
