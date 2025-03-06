package com.crm.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dto.UserDto;
import com.crm.entity.User;
import com.crm.repository.UserRepository; 

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		User newUser = modelMapper.map(userDto, User.class);
		try {
			User userAdded = userRepository.save(newUser);
			return modelMapper.map(userAdded, UserDto.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public UserDto getUser(Long id) {
		try {
			Optional<User> existingUser =  userRepository.findById(id); 
			if(existingUser.isPresent()) {
				return modelMapper.map(existingUser.get(), UserDto.class);
			}
			throw new Exception("User not found!!!"); 
			
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public String deleteUser(Long id) {
		try {
			
			Optional<User> checkDeletion = userRepository.findById(id);
			
			if(checkDeletion.isPresent()) {
				userRepository.deleteById(id);
				return "User Record Deleted Successfully !!!";
			}else {
				throw new Exception("User Not found!!");
			}
		} catch (Exception e) {
			e.getMessage();
			return "User Not found!!!";
		}
	}

	
	
	@Override
	public Object updateUser(Long id, UserDto newValues) {
		try {
			Optional<User> updateObj = userRepository.findById(id);
			if(updateObj.isPresent()) {
				User obj =  updateObj.get();
				
				 User updatedUser = User.builder()
						 	.userId(obj.getUserId())
			                .userName(newValues.getUserName() != null ? newValues.getUserName() : obj.getUserName())
			                .userAge(newValues.getUserAge() != null ? newValues.getUserAge() : obj.getUserAge())
			                .address(newValues.getAddress() != null ? newValues.getAddress() : obj.getAddress())
			                .dob(newValues.getDob() != null ? newValues.getDob() : obj.getDob())
			                .mobileNo(newValues.getMobileNo() != null ? newValues.getMobileNo() : obj.getMobileNo())
			                .build();
				 
				
				System.out.println();
				
				
				User savedUser = userRepository.saveAndFlush(updatedUser);
				return modelMapper.map(savedUser, UserDto.class);
			}else {
				throw new Exception("User is not present");
			}
			
		} catch (Exception e) {
			e.getMessage(); 
			return "User not found!!!";
		} 
	}
	
}
