package service.imple;

import java.util.List;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

/**
*@author  wsz
*@createdTime 2018年3月15日
*/
public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		User login = userDao.login(user);
		if(0 != login.getId()) {
			return login;
		}else {
			return null;
		}
	}

	@Override
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public List<User> findUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public List<User> findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	@Override
	public boolean deleteUser(int id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public boolean updatePwd(int uid, String password) {
		return userDao.updatePwd(uid, password);
	}
	
	@Override
	public boolean updateUser(User user) {
		return true;
	}

	
	@Override
	public boolean deleteUser(User user) {
		return false;
	}

}
