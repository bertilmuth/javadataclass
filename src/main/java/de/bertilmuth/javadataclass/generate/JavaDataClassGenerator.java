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
	private static final String TEMPLATE_FILE_NAME = "javadataclass.ftl";
	
	private Configuration configuration;

	public JavaDataClassGenerator() throws IOException {		
		configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
	}

	public void generateJavaSourceFiles(Collection<ClassSpecification> classSpecifications, File yamlFileDirectory) throws Exception {
		Map<String, Object> dataModel = new HashMap<>();
		
		for (ClassSpecification classSpecification : classSpecifications) {
			dataModel.put("classSpecification", classSpecification);
			Template template = configuration.getTemplate(TEMPLATE_FILE_NAME);
			
			File outputFile = new File(yamlFileDirectory, classSpecification.getClassName() + ".java");
			Writer outputFileWriter = new FileWriter(outputFile);
			
			template.process(dataModel, outputFileWriter);
		}
	}
}
