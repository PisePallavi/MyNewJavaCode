package com.java.orm.association;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table (name = "StudenT_Info")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Student {
	@Id
	private int stud_id;
	private String stud_Name;
	private String stud_email;
	private String stud_add;
	
	@OneToOne
	@JoinColumn(name = "dept_code",unique = true)
	private Department dept;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int stud_id, String stud_Name, String stud_email, String stud_add, Department dept) {
		super();
		this.stud_id = stud_id;
		this.stud_Name = stud_Name;
		this.stud_email = stud_email;
		this.stud_add = stud_add;
		this.dept = dept;
	}
	public int getStud_id() {
		return stud_id;
	}
	public void setStud_id(int stud_id) {
		this.stud_id = stud_id;
	}
	public String getStud_Name() {
		return stud_Name;
	}
	public void setStud_Name(String stud_Name) {
		this.stud_Name = stud_Name;
	}
	public String getStud_email() {
		return stud_email;
	}
	public void setStud_email(String stud_email) {
		this.stud_email = stud_email;
	}
	public String getStud_add() {
		return stud_add;
	}
	public void setStud_add(String stud_add) {
		this.stud_add = stud_add;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "\n Student [stud_id=" + stud_id + ", stud_Name=" + stud_Name + ", stud_email=" + stud_email + ", stud_add="
				+ stud_add + ", dept=" + dept + "]";
	}
	
}

@Entity
@Table (name = "Department_Info")
class Department
{
	@Id
	private int dept_code;
	private String Dept_Name;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(int dept_code, String dept_Name) {
		super();
		this.dept_code = dept_code;
		Dept_Name = dept_Name;
	}
	public int getDept_code() {
		return dept_code;
	}
	public void setDept_code(int dept_code) {
		this.dept_code = dept_code;
	}
	public String getDept_Name() {
		return Dept_Name;
	}
	public void setDept_Name(String dept_Name) {
		Dept_Name = dept_Name;
	}
	@Override
	public String toString() {
		return "\n Department [dept_code=" + dept_code + ", Dept_Name=" + Dept_Name + "]";
	}
}