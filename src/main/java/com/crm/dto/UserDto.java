package com.crm.dto;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
 
@Data
@Builder
//@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto { 
	@Nullable
	Long userId;
	@Nullable
	String userName;
	@Nullable
	String userAge; 
	@Nullable
	String mobileNo; 
	@Nullable
	String address; 
	@Nullable
	LocalDate dob;
}
