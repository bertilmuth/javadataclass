import java.util.*;

public class ${classSpecification.name}{
<#list classSpecification.fieldSpecifications as field>
	private ${field.type} ${field.name};
</#list>

	public ${classSpecification.name}(){
	}
	
<#list classSpecification.fieldSpecifications as field>
	public ${field.type} get${field.name?cap_first}(){
		return ${field.name};
	}
	public void set${field.name?cap_first}(${field.type} ${field.name}){
		this.${field.name} = ${field.name};
	}
</#list>	
}