package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee {
	private int id;
	private String name;
	private String last_name;
	private int age;
	private Integer city_id;

	public Employee(String name, String last_name, int age) {
		this.name = name.trim();
		this.last_name = last_name.trim();
		this.age = Math.abs(age);
		this.city_id = null;
	}

	public Employee(String name, String last_name, int age, Integer city_id) {
		this.name = name.trim();
		this.last_name = last_name.trim();
		this.age = Math.abs(age);
		this.city_id = Math.abs(city_id);
	}
}
