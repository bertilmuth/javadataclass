# Javadataclass
[![Build Status](https://travis-ci.com/bertilmuth/javadataclass.svg?branch=master)](https://travis-ci.com/bertilmuth/javadataclass)
[![Gitter](https://badges.gitter.im/requirementsascode/community.svg)](https://gitter.im/requirementsascode/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

A simple Java source file generator, that takes a YAML file as an input.
You find a description in this [article](https://dev.to/bertilmuth/generating-data-classes-in-java-4cef).
Additionally, have a look at the [Main](https://github.com/bertilmuth/javadataclass/blob/master/src/main/java/de/bertilmuth/javadataclass/Main.java) class to see how to use this library.

# Getting started
Javadataclass is available on Maven Central.

If you are using Maven, include the following in your POM, to use the core:

``` xml
<dependency>
	<groupId>org.requirementsascode</groupId>
	<artifactId>javadataclass</artifactId>
	<version>0.1</version>
</dependency>
```

If you are using Gradle, include the following in your build.gradle, to use the core:

```
implementation 'org.requirementsascode:javadataclass:0.1'
```

At least Java 8 is required to use the library.

# Example output

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
public class User{
	private Name name;
	private Integer age;

	public User(){
	}
	
	public Name getName(){
		return name;
	}
	public void setName(Name name){
		this.name = name;
	}
	public Integer getAge(){
		return age;
	}
	public void setAge(Integer age){
		this.age = age;
	}
}
``` 

`Name.java`:
``` java
public class Name{
	private String firstName;
	private String lastName;

	public Name(){
	}
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
}
```
