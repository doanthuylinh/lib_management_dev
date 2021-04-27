package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.UserEntity;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.AuthenticateException;
import com.example.demo.service.SecurityService;
import com.example.demo.utils.DataUtils;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	private UserDao userDao;
	

	@Override
	public UserEntity getCurrentUserEntity() {
		return userDao.getUserEntityByUsername(this.getCurrentUsername());
	}
	
	@Override
	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return currentUsername;
        }
        return null;
	}
	
	@Override
	public void checkUserWithUserId(Integer userId) throws AuthenticateException {
		UserEntity currentUser = getCurrentUserEntity();
		if (currentUser == null || !userId.equals(currentUser.getUserId())) {
			throw new AuthenticateException("401", "user is not valid");
		}
	}
}
