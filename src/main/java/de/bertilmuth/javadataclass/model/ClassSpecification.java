package de.bertilmuth.javadataclass.model;

import java.util.Collections;
import java.util.List;

public class ClassSpecification {
	private String name;
	private List<FieldSpecification> fieldSpecifications;
	
	public ClassSpecification(String className, List<FieldSpecification> fieldSpecifications) {
		this.name = className;
		this.fieldSpecifications = fieldSpecifications;
	}

	public String getName() {
		return name;
	}

	public List<FieldSpecification> getFieldSpecifications() {
		return Collections.unmodifiableList(fieldSpecifications);
	}
}
