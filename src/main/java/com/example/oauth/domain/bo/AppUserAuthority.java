package com.example.oauth.domain.bo;

//import java.util.Arrays;
//
//import com.fasterxml.jackson.annotation.JsonCreator;

public enum AppUserAuthority {
	
	ADMIN(1), NORMAL(2);
	
	private int userLevel;
	
	private AppUserAuthority(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getUserLevel() {
		return userLevel;
	}
	
	public static AppUserAuthority toAppUserAuthority(int userLevel) {
		switch (userLevel) {
		case 1:
			return AppUserAuthority.ADMIN;
		default:
			return AppUserAuthority.NORMAL;
		}
	}
	
//	自定義反序列化的過程
//	@JsonCreator
//	public AppUserAuthority fromString(String key) {
//		return Arrays.stream(values()).filter(value -> value.name().equalsIgnoreCase(key)).findFirst().orElse(null);
//	}
}
