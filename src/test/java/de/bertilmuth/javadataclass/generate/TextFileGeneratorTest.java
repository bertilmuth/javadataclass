package de.bertilmuth.javadataclass.generate;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.model.FieldSpecification;

public class TextFileGeneratorTest {
	private static final String FIELDLESS_CLASS = "FieldlessClass";
	private static final String USER_CLASS = "User";

	private TextFileGenerator textFileGenerator;

	@Before
	public void setUp() throws Exception {
		textFileGenerator = new TextFileGenerator();
	}

	@Test
	public void generateTwoClasses() throws Exception {
		ClassSpecification fieldlessClassSpecification = new ClassSpecification(FIELDLESS_CLASS);
		ClassSpecification userClassSpecification = new ClassSpecification(USER_CLASS);
		userClassSpecification.addFieldSpecification(new FieldSpecification("name", "String"));
		userClassSpecification.addFieldSpecification(new FieldSpecification("age", "Integer"));

		List<ClassSpecification> classSpecifications = Arrays.asList(fieldlessClassSpecification,
				userClassSpecification);

		String templateFileName = "javadataclass.ftl";
		textFileGenerator.generate(classSpecifications, templateFileName);

		assertTrue(new File(USER_CLASS + ".java").exists());
		assertTrue(new File(FIELDLESS_CLASS + ".java").exists());
	}
}
