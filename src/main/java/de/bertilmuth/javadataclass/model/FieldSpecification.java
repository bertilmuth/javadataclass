package de.bertilmuth.javadataclass.model;

public class FieldSpecification {
	private String fieldName;
	private String fieldType;

	public FieldSpecification(String fieldName, String fieldType) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

}
