package de.bertilmuth.javadataclass.read;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
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
		
		classSpecifications = 
			mapOfClassNameToListOfFieldSpecifications(yamlClassSpecifications).entrySet().stream()
				.map(e -> new ClassSpecification(e.getKey(), e.getValue()))
				.collect(toList());

		return classSpecifications;
	}

	private Map<String, List<FieldSpecification>> mapOfClassNameToListOfFieldSpecifications(
			Map<String, Map<String, String>> yamlClassSpecificationsOrNull) {

		if (yamlClassSpecificationsOrNull == null)
			return new HashMap<>();

		return yamlClassSpecificationsOrNull.entrySet().stream()
				.collect(toMap(className(), listOfFieldSpecifications()));
	}
	
	private Function<? super Entry<String, Map<String, String>>, ? extends String> className() {
		return outerMap -> outerMap.getKey();
	}

	private Function<? super Entry<String, Map<String, String>>, ? extends List<FieldSpecification>> listOfFieldSpecifications() {
		return outerMap -> listOfFieldSpecifications(outerMap.getValue());
	}

	private List<FieldSpecification> listOfFieldSpecifications(Map<String, String> yamlFieldSpecificationsOrNull) {
		if (yamlFieldSpecificationsOrNull == null)
			return new ArrayList<>();

		List<FieldSpecification> fieldSpecifications = yamlFieldSpecificationsOrNull.entrySet().stream()
				.map(e -> new FieldSpecification(e.getKey(), e.getValue())).collect(Collectors.toList());

		return fieldSpecifications;
	}
}
