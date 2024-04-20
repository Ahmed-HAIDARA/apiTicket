package com.tickets.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tickets.api.model.Dbuser;
import com.tickets.api.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	public Iterable<Dbuser> getUsers() {
        return userRepository.findAll();
    }
	
	public Dbuser saveUser(Dbuser user) {
		Dbuser savedUser = userRepository.save(user);
        return savedUser;
    }

    public Optional<Dbuser> getUser(final Long id) {
        return userRepository.findById(id);
    }    

}
