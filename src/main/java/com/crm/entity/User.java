package com.crm.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long userId;
	
	@Column(name = "Name")
	String userName;
	
	@Column(name = "Age")
	String userAge;
	
	@Column(name = "MobileNo")
	String mobileNo;
	
	@Column(name = "Address")
	String address;
	
	@Column(name = "DOB")
	LocalDate dob;

}
