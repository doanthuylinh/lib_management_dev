package com.example.demo.service;

import com.example.demo.bean.UserEntity;
import com.example.demo.exception.AuthenticateException;

public interface SecurityService {

	UserEntity getCurrentUserEntity();

	String getCurrentUsername();

	void checkUserWithUserId(Integer userId) throws AuthenticateException;

}
