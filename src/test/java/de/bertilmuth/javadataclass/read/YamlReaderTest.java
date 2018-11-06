package de.bertilmuth.javadataclass.read;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import de.bertilmuth.javadataclass.model.ClassSpecification;
import de.bertilmuth.javadataclass.model.FieldSpecification;
import de.bertilmuth.javadataclass.read.YamlReader;

public class YamlReaderTest {

	@Test
	public void readsEmptyClassSpecifications() {
		String emptyClasses = "";
		List<ClassSpecification> classSpecifications = readClassSpecifications(emptyClasses);
		assertTrue(classSpecifications.isEmpty());
	}
	
	@Test
	public void readsSingleClassSpecificationWithoutFields() {
		String classSpecificationString =  "Order:\r\n";
		List<ClassSpecification> classSpecifications = readClassSpecifications(classSpecificationString);
		assertEquals(1, classSpecifications.size());
		assertEquals("Order", classSpecifications.get(0).getClassName());
	}
	
	@Test
	public void readsTwoClassSpecificationWithoutFields() {
		String classSpecificationString =  "Order:\r\nCustomer:\r\n";
		List<ClassSpecification> classSpecifications = readClassSpecifications(classSpecificationString);
		assertEquals(2, classSpecifications.size());
		assertEquals("Order", classSpecifications.get(0).getClassName());
		assertEquals("Customer", classSpecifications.get(1).getClassName());
	}
	
	@Test
	public void readsSingleClassSpecificationWithSingleField() {
		String classSpecificationString =  "Order:\r\n orderId: Long\r\n";
		List<ClassSpecification> classSpecifications = readClassSpecifications(classSpecificationString);
		List<FieldSpecification> orderFields = classSpecifications.get(0).getFieldSpecifications();
		assertEquals(1, orderFields.size());
		assertEquals("orderId", orderFields.get(0).getFieldName());
		assertEquals("Long", orderFields.get(0).getFieldType());
	}
	
	@Test
	public void readsSingleClassSpecificationWithTwoFields() {
		String classSpecificationString =  "Order:\r\n orderId: Long\r\n orderDate: Date";
		List<ClassSpecification> classSpecifications = readClassSpecifications(classSpecificationString);
		List<FieldSpecification> orderFields = classSpecifications.get(0).getFieldSpecifications();
		assertEquals(2, orderFields.size());
		assertEquals("orderId", orderFields.get(0).getFieldName());
		assertEquals("Long", orderFields.get(0).getFieldType());
		assertEquals("orderDate", orderFields.get(1).getFieldName());
		assertEquals("Date", orderFields.get(1).getFieldType());
	}
	
	@Test
	public void readsTwoClassSpecificationsWithFields() {
		String classSpecificationString =  "Order:\r\n orderId: Long\r\n orderDate: Date\r\nCustomer:\r\n firstName: String";
		List<ClassSpecification> classSpecifications = readClassSpecifications(classSpecificationString);
		List<FieldSpecification> orderFields = classSpecifications.get(0).getFieldSpecifications();
		List<FieldSpecification> customerFields = classSpecifications.get(1).getFieldSpecifications();

		assertEquals(2, orderFields.size());
		assertEquals("orderId", orderFields.get(0).getFieldName());
		assertEquals("Long", orderFields.get(0).getFieldType());
		assertEquals("orderDate", orderFields.get(1).getFieldName());
		assertEquals("Date", orderFields.get(1).getFieldType());
		
		assertEquals(1, customerFields.size());
		assertEquals("firstName", customerFields.get(0).getFieldName());
		assertEquals("String", customerFields.get(0).getFieldType());
	}

	private List<ClassSpecification> readClassSpecifications(String dataClassString) {
		StringReader reader = new StringReader(dataClassString);
		List<ClassSpecification> requirements = new YamlReader().readClassSpecifications(reader);
		return requirements;
	}
}