package de.bertilmuth.javadataclass.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.model.FieldSpecification;

public class YamlClassSpecificationReader {
	public List<ClassSpecification> read(File yamlFile) throws FileNotFoundException {
		return read(new FileReader(yamlFile));
	}

	public List<ClassSpecification> read(Reader reader) {
		Map<String, Map<String, String>> yamlClassSpecifications = readYamlClassSpecifications(reader);
		List<ClassSpecification> classSpecifications = createClassSpecificationsFrom(yamlClassSpecifications);
		return classSpecifications;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Map<String, String>> readYamlClassSpecifications(Reader reader) {
		Yaml yaml = new Yaml();

		// Read in the complete YAML file to a map of strings to a map of strings to strings
		Map<String, Map<String, String>> yamlClassSpecifications = 
			(Map<String, Map<String, String>>) yaml.load(reader);

		return yamlClassSpecifications;
	}

	private List<ClassSpecification> createClassSpecificationsFrom(
			Map<String, Map<String, String>> yamlClassSpecifications) {
		List<ClassSpecification> classSpecifications;
		
		if(yamlClassSpecifications != null) {
			classSpecifications = mapOfClassNameToFieldSpecifications(yamlClassSpecifications).entrySet().stream()
				.map(e -> new ClassSpecification(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		} else {
			classSpecifications = new ArrayList<>();
		}

		return classSpecifications;
	}

	private Map<String, List<FieldSpecification>> mapOfClassNameToFieldSpecifications(
			Map<String, Map<String, String>> yamlClassSpecifications) {
		return yamlClassSpecifications.entrySet().stream()
			.collect(Collectors.toMap(outerMap -> outerMap.getKey(), outerMap -> listOfFieldSpecifications(outerMap.getValue())));
	}
	
	private List<FieldSpecification> listOfFieldSpecifications(Map<String, String> yamlFieldSpecifications) {
		List<FieldSpecification> fieldSpecifications;
		
		if (yamlFieldSpecifications != null) {
			fieldSpecifications = yamlFieldSpecifications.entrySet().stream()
				.map(e -> new FieldSpecification(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		} else {
			fieldSpecifications = new ArrayList<>();
		}

		return fieldSpecifications;
	}
}
