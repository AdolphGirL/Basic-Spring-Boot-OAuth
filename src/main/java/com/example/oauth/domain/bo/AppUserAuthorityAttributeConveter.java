package com.example.oauth.domain.bo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AppUserAuthorityAttributeConveter implements AttributeConverter<AppUserAuthority, Integer>{

	@Override
	public Integer convertToDatabaseColumn(AppUserAuthority attribute) {
		return attribute.getUserLevel();
	}

	@Override
	public AppUserAuthority convertToEntityAttribute(Integer dbData) {
		return AppUserAuthority.toAppUserAuthority(dbData);
	}
	
}
