package service;

import java.util.List;

import entity.User;

/**
*@author  wsz
*@createdTime 2018年3月15日
*/
public interface UserService {

	User login(User user);
	
	boolean createUser(User user);
	
	List<User> getAllUser();
	
	List<User> findUserById(int id);
	
	List<User> findUserByName(String name);
	
	boolean updateUser(User user);
	
	boolean updatePwd(int uid,String password);
	
	boolean deleteUser(User user);
	
	boolean deleteUser(int id);
}
