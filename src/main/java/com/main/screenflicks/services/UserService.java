package com.main.screenflicks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.screenflicks.models.Movie;
import com.main.screenflicks.models.User;
import com.main.screenflicks.models.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService

{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;
    
    @Autowired 
	private MongoTemplate mongoTemplate;

    public User addUser(User user)
    {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	User insertedUser = userRepository.insert(user);
        return insertedUser;
    }

    public List<User> getUsers()
    {
    	return userRepository.findAll();
    }

    public User getUser(User user)
    {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("email").is(user.getEmail()).and("password").is(passwordEncoder.encode(user.getPassword())));
    	User user1= mongoTemplate.findOne(query,User.class);
    	return user1; 
    }
}
