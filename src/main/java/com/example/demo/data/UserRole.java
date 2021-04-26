package com.example.demo.data;

public enum UserRole {

	UNDEFINED(-1),
    MEMBER(0),
    ADMIN(1);
	
	private final Integer value;

	UserRole(Integer i) {
		value = i;
	}
	
	public Integer value() {
		return value;
	}
	
	public static UserRole parse(Integer i) {
		UserRole state = UserRole.UNDEFINED;
		for (UserRole item : UserRole.values()) {
			if (item.value().equals(i)) {
				state = item;
				break;
			}
		}
		
		return state;
	}
}