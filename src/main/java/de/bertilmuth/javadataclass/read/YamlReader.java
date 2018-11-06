package de.bertilmuth.javadataclass.read;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.model.FieldSpecification;

public class YamlReader {

	public List<ClassSpecification> readClassSpecifications(Reader reader) {
		Map<String, Map<String, String>> yamlClassSpecifications = readYamlClassSpecifications(reader);
		List<ClassSpecification> classSpecifications = createClassSpecificationsFrom(yamlClassSpecifications);
		return classSpecifications;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Map<String, String>> readYamlClassSpecifications(Reader reader) {
		Yaml yaml = new Yaml();
		Map<String, Map<String, String>> yamlClassSpecifications = (Map<String, Map<String, String>>) yaml.load(reader);
		return yamlClassSpecifications;
	}

	private List<ClassSpecification> createClassSpecificationsFrom(Map<String, Map<String, String>> yamlClassSpecifications) {
		List<ClassSpecification> classSpecifications = new ArrayList<>();

		if (yamlClassSpecifications != null) {
			for (String yamlClassName : yamlClassSpecifications.keySet()) {
				Map<String, String> yamlFieldSpecifications = yamlClassSpecifications.get(yamlClassName);
				ClassSpecification classSpecification = createClassSpecification(yamlClassName, yamlFieldSpecifications);
				classSpecifications.add(classSpecification);
			}
		}

		return classSpecifications;
	}

	private ClassSpecification createClassSpecification(String yamlClassName, Map<String, String> yamlFieldSpecifications) {
		final ClassSpecification classSpecification = new ClassSpecification(yamlClassName);
		
		if (yamlFieldSpecifications != null) {
			for (String yamlFieldName : yamlFieldSpecifications.keySet()) {
				String yamlFieldType = (String) yamlFieldSpecifications.get(yamlFieldName);
				FieldSpecification fieldSpecification = new FieldSpecification(yamlFieldName, yamlFieldType);
				classSpecification.addFieldSpecification(fieldSpecification);
			}
		}
		
		return classSpecification;
	}
}
