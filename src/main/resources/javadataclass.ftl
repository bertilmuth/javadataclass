import java.util.*;

public class ${classSpecification.className}{
<#list classSpecification.fieldSpecifications as f>
	private ${f.fieldType} ${f.fieldName};
</#list>

	public ${classSpecification.className}(){
	}
	
<#list classSpecification.fieldSpecifications as f>
	public ${f.fieldType} get${f.fieldName?cap_first}(){
		return ${f.fieldName};
	}
</#list>	
}