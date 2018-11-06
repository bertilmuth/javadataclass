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

public class TextFileGenerator {
	private File templateDirectory;
	private Configuration configuration;

	public TextFileGenerator(File templateDirectory) throws IOException {
		this.templateDirectory = templateDirectory;
		
		configuration = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setDirectoryForTemplateLoading(templateDirectory);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
	}

	public void generate(Collection<ClassSpecification> classSpecifications, String templateFileName) throws Exception {
		Map<String, Object> dataModel = new HashMap<>();
		
		for (ClassSpecification classSpecification : classSpecifications) {
			dataModel.put("classSpecification", classSpecification);
			Template template = configuration.getTemplate(templateFileName);
			
			File outputFile = new File(templateDirectory, classSpecification.getClassName() + ".java");
			Writer outputFileWriter = new FileWriter(outputFile);
			
			template.process(dataModel, outputFileWriter);
		}
	}
}
