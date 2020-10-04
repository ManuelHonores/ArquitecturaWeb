package entity;

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
public class Career {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column
	private int registered;
	@OneToMany(mappedBy="career")
	@JsonIgnore
	private List<Registration> student;
	public Career() {
		super();
	}
	public Career(String name) {
		super();
		this.name = name;
		this.registered = 0;
		this.student = new ArrayList<Registration>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRegistered() {
		return registered;
	}
	public void setRegistered(int registered) {
		this.registered = registered;
	}
	@Override
	public String toString() {
		return "Career [id=" + id + ", name=" + name + ", registered=" + registered + "]";
	}
	public List<Registration> getStudent() {
		return student;
	}
	public Registration registerStudent(Student s, boolean finished, Integer registrationYear, Integer graduationYear) {
		
		Registration t = new Registration(s,this,finished,registrationYear,graduationYear);
		this.student.add(t);
		this.registered ++;
		return t;
	}
}
