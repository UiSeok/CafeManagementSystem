package tmon.data;

import java.util.Date;

public class EmployeeData {
	
	protected int id;
	protected String name;
	protected int age;
	protected Date join_date;
	protected Date retire_date;
	protected int salary;
	
	public EmployeeData() {
		
	}
	
	
	public EmployeeData(int id, String name, int age, Date join_date,
			Date retire_date, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.join_date = join_date;
		this.retire_date = retire_date;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public Date getRetire_date() {
		return retire_date;
	}
	public void setRetire_date(Date retire_date) {
		this.retire_date = retire_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
