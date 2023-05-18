package QLNV.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "staffs")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100 )
	private String name;
	@Column(length = 100 )
	private String photo;
	@Column(length = 10 )
	private String gender;
	@Column(length = 15 )
	private String phone;
	private Long salary;
	@Column(length = 1000 )
	private String note;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date birthday;
	@ManyToOne
	@JoinColumn( name = "departId")
	private Depart depart;
	
	
	public Staff() {
		super();
	}
	
	public Staff(Long id, String name, String photo, String gender, String phone, Long salary, String note,
			Date birthday, Depart depart) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.gender = gender;
		this.phone = phone;
		this.salary = salary;
		this.note = note;
		this.birthday = birthday;
		this.depart = depart;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public Long getId() {
		return id;
	}
	
}
