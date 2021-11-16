package com.example.oauth.domain.bo;

import java.util.List;

import javax.persistence.ElementCollection;

//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "AppUser")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String emailAddress;
	private String password;
	private String name;
	
//	基礎方式
//	@Enumerated(EnumType.ORDINAL)
//	private AppUserAuthority autho;
	
//	if autoApply = true, mapping apply to the type automatically
//	private AppUserAuthority autho;
	
//	沒有宣告table，會預設產製一個
//	Collection方式，easy way
//	@ElementCollection(targetClass=AppUserAuthority.class)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<AppUserAuthority> authorities;
	
//	複雜方式 =============== 
//	@ElementCollection(targetClass=AppUserAuthority.class)
	
//	不需要，已封裝
//	@Enumerated(EnumType.STRING)						// Possibly optional (I'm not sure) but defaults to ORDINAL.
//	不需要，已封裝
	
//	@CollectionTable(name="person_interest")
//	@Column(name="interest")							// Column name in person_interest
//	Collection<InterestsEnum> interests;
	
}
