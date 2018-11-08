package de.bertilmuth.javadataclass.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class JavaDataClassGenerator {	
	private Configuration configuration;

	public JavaDataClassGenerator() throws IOException {		
		configuration = new Configuration(Configuration.VERSION_2_3_28);
		
		// Set the root of the class path ("") as the location to find templates
		configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "");
		
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
	}

	public void generateJavaSourceFiles(Collection<ClassSpecification> classSpecifications, File yamlFileDirectory) throws Exception {
		Map<String, Object> freemarkerDataModel = new HashMap<>();
		
		// Get the template to generate Java source files
		Template template = configuration.getTemplate("javadataclass.ftl");
		
		for (ClassSpecification classSpecification : classSpecifications) {
			// Put the classSpecification into the data model.
			// It can  be accessed in the template through ${classSpecification}
			freemarkerDataModel.put("classSpecification", classSpecification);
			
			// The Java source file will be generated in the same directory as the YAML file
			File javaSourceFile = new File(yamlFileDirectory, classSpecification.getName() + ".java");
			Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
			
			// Generate the Java source file
			template.process(freemarkerDataModel, javaSourceFileWriter);
		}
	}
}
