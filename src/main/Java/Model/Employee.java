package Model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Employee {
	private int id;
	private String name;
	private String lastName;
	private int age;
	private Integer cityID;

	public Employee(String name, String lastName, int age) {
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.age = Math.abs(age);
		this.cityID = null;
	}

	public Employee(String name, String lastName, int age, Integer cityID) {
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.age = Math.abs(age);
		this.cityID = Math.abs(cityID);
	}
}
