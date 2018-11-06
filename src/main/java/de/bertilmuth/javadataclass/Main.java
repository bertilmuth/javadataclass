package de.bertilmuth.javadataclass;

import java.io.File;
import java.util.List;

import de.bertilmuth.javadataclass.generate.JavaDataClassGenerator;
import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.read.YamlClassSpecificationReader;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Please supply exactly one argument, the absolute path of the YAML file.");
			return;
		}
		final String yamlFilePath = args[0];
		final File yamlFile = new File(yamlFilePath);
		final File yamlDirectory = yamlFile.getParentFile();

		// Step 1: Read in a YAML file, into an in-memory model of class specifications
		YamlClassSpecificationReader yamlReader = new YamlClassSpecificationReader();
		List<ClassSpecification> classSpecifications = yamlReader.read(yamlFile);

		// Step 2: Generate Java source files from the class specifications
		JavaDataClassGenerator javaDataClassGenerator = new JavaDataClassGenerator();
		javaDataClassGenerator.generateJavaSourceFiles(classSpecifications, yamlDirectory);

		System.out.println("Successfully generated class files.");
	}
}
