package de.bertilmuth.javadataclass;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import de.bertilmuth.javadataclass.generate.JavaDataClassGenerator;
import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.read.YamlReader;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Please supply exactly one argument, the absolute path of the YAML file.");
			return;
		}
		final String yamlFilePath = args[0];
		final FileReader yamlFileReader = new FileReader(yamlFilePath);
		final File yamlFileDirectory = new File(yamlFilePath).getParentFile();

		YamlReader yamlReader = new YamlReader();
		List<ClassSpecification> classSpecifications = yamlReader.readClassSpecifications(yamlFileReader);

		JavaDataClassGenerator javaDataClassGenerator = new JavaDataClassGenerator();
		javaDataClassGenerator.generateJavaSourceFiles(classSpecifications, yamlFileDirectory);

		System.out.println("Successfully generated class files.");
	}
}
