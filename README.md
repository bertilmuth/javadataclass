# javadataclass
A simple Java source file generator, that takes a YAML file as an input.

Here's the content of an example YAML file:
``` yaml
User:
    name: Name
    age: Integer
    
Name:
    firstName: String
    lastName: String
``` 

From this file, 2 Java source files will be generated, `User.java` and `Name.java`.

For the sake of simplicity, the Java source files will be generated in the same directory as the YAML file.

The contents of these two files are:

`User.java`:
``` java
import java.util.*;

public class User{
	private Name name;
	private Integer age;

	public User(){
	}
	
	public Name getName(){
		return name;
	}
	public Integer getAge(){
		return age;
	}
}
``` 

`Name.java`:
``` java
import java.util.*;

public class Name{
	private String firstName;
	private String lastName;

	public Name(){
	}
	
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
}
```
