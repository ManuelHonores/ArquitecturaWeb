package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student implements Serializable {
	private static final long serialVersionUID = -6088907832817414299L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer lu;
	@Column
	private Integer dni;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private int age;
	@Column
	private char gender;
	@Column
	private String city;
	@OneToMany(mappedBy="student")
	@JsonIgnore
	private List<Registration> career;
	public Student() {
		super();
	}
	public Student(Integer dni, String name, String surname, int age, char gender, String city) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.career = new ArrayList<Registration>();
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Integer getLu() {
		return lu;
	}
	public void setLu(Integer lu) {
		this.lu = lu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Nombre:" +name + ", Apellido= " + surname + ", DNI=" + dni + ", LU=" + lu + ", Edad=" + age
				+ ", Genero =" + gender + ", Ciudad=" + city;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Registration> getCareer() {
		return career;
	}
}
