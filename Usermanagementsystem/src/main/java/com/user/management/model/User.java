package com.user.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	@NotBlank(message = "please enter firstname")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "dob")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;	
	
	@Column(name="is_active")
	 private Boolean isactive=true;
	    
	@Column(name="created_on")
	 private LocalDateTime createdon; 
	
	@Column(name="mobile_no",unique = true)
	private String mobileno;
	
	@Column(name="password")
	private String password;
	



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fadd_id",referencedColumnName = "id")
	private List<Address> address=new ArrayList<>();

	

	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDOB() {
		return DOB;
	}


	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}


	public Boolean getIsactive() {
		return isactive;
	}


	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}


	public LocalDateTime getCreatedon() {
		return createdon;
	}


	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}


	public String getMobileno() {
		return mobileno;
	}


	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public User(Long id, @NotBlank(message = "please enter firstname") String firstName, String lastName, String email,
			Boolean isactive, String mobileno) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isactive = isactive;
		this.mobileno = mobileno;
	}
	

	public User(Long id, String firstName, String lastName, String email, LocalDate dOB, String mobileno) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		DOB = dOB;
		this.mobileno = mobileno;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", DOB="
				+ DOB + ", isactive=" + isactive + ", createdon=" + createdon + ", mobileno=" + mobileno + ", password="
				+ password + ", address=" + address + "]";
	}

	
	@PrePersist
	public void setCreatedon1() {
	    this.createdon = LocalDateTime.now();
	}

	@PreUpdate
	public void setCreatedon() {
	    this.createdon = LocalDateTime.now();
	}
	
}
