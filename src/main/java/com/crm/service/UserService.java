package com.crm.service;

import com.crm.dto.UserDto;

public interface UserService {
	
	/**
	 * Add the user by providing new User DTO Object.
	 * 
	 * @param new values as UserDto object type.
	 * @return Save user object if data save succssfully in db, null otherwise.
	 */
	public UserDto addUser(UserDto userDto);
	
	/**
	 * Fetch the user by providing new User DTO Object.
	 *  
	 * @return user object if data present in db, null otherwise.
	 */
	public UserDto getUser(Long id);
	
	/**
	 * Delete the user with provided user id.
	 * 
	 * @return String value w.r.t operation.
	 */
	public String deleteUser(Long id);

	/**
	 * Update the user by providing userId and new Updated values.
	 *
	 * @param id must not be null.
	 * @param new values as UserDto object type.
	 * @return {@literal User Object} if an entity with the given id exists, {@literal String} otherwise.
	 */
	public Object updateUser(Long id, UserDto newValues);

}
