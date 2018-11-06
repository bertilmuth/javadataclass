package de.bertilmuth.javadataclass.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassSpecification {
	private String className;
	private List<FieldSpecification> fieldSpecifications;

	public ClassSpecification(String className) {
		this.className = className;
		this.fieldSpecifications = new ArrayList<>();
	}

	public Object getClassName() {
		return className;
	}

	public List<FieldSpecification> getFieldSpecifications() {
		return Collections.unmodifiableList(fieldSpecifications);
	}

	public void addFieldSpecification(FieldSpecification fieldSpecification) {
		fieldSpecifications.add(fieldSpecification);
	}
}
