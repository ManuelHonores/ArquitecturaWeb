package entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
@Entity
public class Registration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7881603150879655580L;
	@Id
	@MapsId
	@ManyToOne
	private Student student;
	@Id
	@MapsId
	@ManyToOne
	private Career career;
	@Column
	private boolean finished;
	@Column
	private int antiquity;
	@Column
	private Integer registrationYear;
	@Column
	private Integer graduationYear;
	
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Registration(Student student, Career career, boolean finished, Integer registrationYear,
			Integer graduationYear) {
		super();
		this.student = student;
		this.career = career;
		this.finished = finished;
		if(graduationYear == null) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			this.antiquity = year - registrationYear;	
		}
		else {
			this.antiquity = graduationYear - registrationYear;
		}
		this.registrationYear = registrationYear;
		this.graduationYear = graduationYear;
	}

	public Student getStudient() {
		return student;
	}
	public void setStudient(Student studient) {
		this.student = studient;
	}
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public int getAntiquity() {
		return antiquity;
	}
	public void setAntiquity(int antiquity) {
		this.antiquity = antiquity;
	}
	@Override
	public String toString() {
		return "StudentCareer [student=" + student + ", career=" + career + ", finished=" + finished + ", antiquity="
				+ antiquity + "]";
	}
}
