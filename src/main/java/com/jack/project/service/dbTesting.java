package com.jack.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jack.project.model.*;
import com.jack.project.repository.*;

@Transactional
@Service
public class dbTesting {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(roleUser.getName()));

		User user = new User();
		String password = "test";
		user.setUsername("jack96");
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEmail("jack@gmail.com");
		user.setFirstname("Jack");
		user.setLastname("Kehoe");
		user.setSchool("Naas CBS");
		
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleUser);
		user.setRoles(roles);
		userRepository.save(user);
		
		User user2 = new User();
		String password2 = "test";
		user2.setUsername("kevin96");
		user2.setPassword(bCryptPasswordEncoder.encode(password2));
		user2.setEmail("kevin@email.com");
		user2.setFirstname("Kevin");
		user2.setLastname("O'Dwyer");
		user2.setSchool("Tallaght Community School");
		
		user2.setRoles(roles);
		userRepository.save(user2);	
		
		
//		Mentor mentor = new Mentor();
//		String password3 = "test";
//		mentor.setUsername("Mentor1");
//		mentor.setPassword(bCryptPasswordEncoder.encode(password3));
//		mentor.setEmail("mentor@gmail.com");
//		mentor.setFirstname("Brian");
//		mentor.setLastname("Walzer");
//		mentor.setSchool("Naas CBS");
//		
//		mentor.setRoles(roles);
//		mentorRepository.save(mentor);
		

	}

}