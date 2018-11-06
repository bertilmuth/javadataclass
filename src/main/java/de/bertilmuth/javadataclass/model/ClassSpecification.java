package de.bertilmuth.javadataclass.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassSpecification {
	private String name;
	private List<FieldSpecification> fieldSpecifications;

	public ClassSpecification(String className) {
		this.name = className;
		this.fieldSpecifications = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<FieldSpecification> getFieldSpecifications() {
		return Collections.unmodifiableList(fieldSpecifications);
	}

	public void addFieldSpecification(FieldSpecification fieldSpecification) {
		fieldSpecifications.add(fieldSpecification);
	}
}
