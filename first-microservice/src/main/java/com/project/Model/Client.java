package com.project.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Client {
	@ApiModelProperty(notes = "The database generated user ID")
	@Id
	@GeneratedValue
	private Long id;
	@ApiModelProperty(notes = "User`s login", required = true)
	@NotEmpty(message = "Please enter login!")
	@Size(min = 4, max = 15, message = "4-15 Signs")
//	@Column(unique = true)
	private String login;
	@ApiModelProperty(notes = "User`s password", required = true)
	@NotEmpty(message = "Please enter login!")
	@Size(min = 3, max = 80, message = "3-15 Signs")
	private String password;
	@JsonProperty(access = Access.AUTO)
	@ApiModelProperty(notes = "This`s user or admin", required = true)
	private String role;
	@ApiModelProperty(notes = "Name")
	private String firstName;
	@ApiModelProperty(notes = "Surname", required = true)
	private String lastName;
	@ApiModelProperty(notes = "Address e-mail", required = true)
	@NotEmpty
	@Email(message = "Wrong email")
	private String email;
	@JsonProperty(access = Access.AUTO)
	private Boolean active = true;
	@OneToOne(mappedBy = "client")
	private Reservation reservation;

	public Client() {
	}

	public Client(Client client) {
	}

	public Client(String login, String password, String firstName, String lastName, String email, Boolean active) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
	}

	public Client(Long id, String login, String password, String role, String firstName, String lastName, String email,
			Boolean active) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
