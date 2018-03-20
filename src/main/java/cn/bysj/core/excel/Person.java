package cn.bysj.core.excel;

import cn.bysj.core.web.excel.annotation.CellConfig;
import cn.bysj.core.web.excel.annotation.OutAlias;

import com.google.common.base.MoreObjects;
@OutAlias("xiaoshou")
public  class Person {

	public Person(int age, String name, Double salary) {
		super();
		this.age = age;
		this.name = name;
		this.salary = salary;
	}

	public Person() {
		super();
	}

	@CellConfig(index = 1, aliasName = "年龄")
	private int age;
	@CellConfig(index = 0)
	private String name;
	@CellConfig(index = 3)
	private Double salary;
	private transient boolean testProperty = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public Double getSalary() {
		return salary;
	}

	public String toString() {
		return MoreObjects.toStringHelper(this.getClass()).omitNullValues()
				.add("name", name).add("age", age).add("salary", salary)
				.toString();
	}
}
