package de.bertilmuth.javadataclass.generate;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.model.FieldSpecification;

public class JavaDataClassGeneratorTest {
	private static final String FIELDLESS_CLASS = "FieldlessClass";
	private static final String USER_CLASS = "User";

	private JavaDataClassGenerator javaDataClassGenerator;
	private File temporaryDirectory;

	@Before
	public void setUp() throws Exception {
		javaDataClassGenerator = new JavaDataClassGenerator();
		temporaryDirectory = Files.createTempDirectory("JAVADATACLASS_").toFile();
	}

	@Test
	public void generateTwoClasses() throws Exception {
		ClassSpecification fieldlessClassSpecification = new ClassSpecification(FIELDLESS_CLASS, new ArrayList<>());

		List<FieldSpecification> userFieldSpecifications = Arrays.asList(new FieldSpecification("name", "String"),
				new FieldSpecification("age", "Integer"));
		ClassSpecification userClassSpecification = new ClassSpecification(USER_CLASS, userFieldSpecifications);

		List<ClassSpecification> classSpecifications = Arrays.asList(fieldlessClassSpecification,
				userClassSpecification);

		javaDataClassGenerator.generateJavaSourceFiles(classSpecifications, temporaryDirectory);

		assertTrue(new File(temporaryDirectory, USER_CLASS + ".java").exists());
		assertTrue(new File(temporaryDirectory, FIELDLESS_CLASS + ".java").exists());
		
		System.out.println("Successfully generated class files to: " + temporaryDirectory.getAbsolutePath());
	}
}
