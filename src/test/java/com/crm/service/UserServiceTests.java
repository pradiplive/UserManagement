package com.crm.service;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.crm.dto.UserDto;
import com.crm.entity.User;
import com.crm.repository.UserRepository;
import com.crm.service.UserServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;
	private UserDto userDto;

	@BeforeEach
	public void setUp() {
		user = new User(1L, "Rahul", "33", "987898789", "Keral", LocalDate.parse("1998-09-05"));
		userDto = new UserDto(1L, "Rahul", "33", "987898789", "Keral", LocalDate.parse("1998-09-05"));
	}

	@Test
	@DisplayName("should fetch the user when user with given userId already present")
	void getUserTest() {
		 
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		UserDto userDto = UserDto.builder()
				.userName("Rahul")
				.userAge("33")
				.mobileNo("9897978697")
				.address("TamilNadu")
				.dob(LocalDate.parse("1998-09-04"))
				.build();
		
//	    userDto.setUserName("Rahul");
//	    userDto.setUserAge("33");
//	    userDto.setMobileNo("987898789");
//	    userDto.setAddress("Keral");
//	    userDto.setDob(LocalDate.parse("1998-09-05"));
	    
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		
		UserDto fetchObj =  userService.getUser(1L);
		
		assertNotNull(fetchObj);
		assertEquals("Rahul", fetchObj.getUserName());
	}

	@Test
	@DisplayName("should return null when user with given userId not present")
	void getUserForInvalidUserIdTest() {
		when(userRepository.findById(101L)).thenReturn(null);
		
		UserDto obj =  userService.getUser(101L);
		
		assertNull(obj);  
	}

	@Test
	@DisplayName("should return user when user successfully save in db")
	void checkAddUserTest() {
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
		
		UserDto returnObj =  userService.addUser(userDto);
		
		assertNotNull(returnObj);
		assertEquals(returnObj.getUserName(), userDto.getUserName());
		assertSame(userDto, returnObj);
	}
	
	@Test
	@DisplayName("Should catch the error when it throws Exception while storing new user")
	void checkInvalidAddUserTest() {
		when(modelMapper.map(userDto, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenThrow(RuntimeException.class);
		
		UserDto returnObj = userService.addUser(userDto);
		
		assertNull(returnObj);	
		
	}
	
	@Test
	@DisplayName("should delete the user when valid userId is provided")
	void deleteUserTest() {
		when(userRepository.findById(11L)).thenReturn(Optional.of(user));
		
		String returnAns = userService.deleteUser(11L);
		
		String expected = "User Record Deleted Successfully !!!";
		assertEquals(expected, returnAns);
		verify(userRepository, times(1)).deleteById(11L);
	}
	
	@Test
	@DisplayName("should throw exception when user with given userId not present")
	void deleteUserWithInvalidTest() {
		when(userRepository.findById(11L)).thenReturn(null);
		
		String returnAns = userService.deleteUser(11L);
		
		String expected = "User Not found!!!";
		assertEquals(expected, returnAns);
		verify(userRepository, times(0)).deleteById(11L);
	}

}
