package com.tickets.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tickets.api.model.Dbuser;
import com.tickets.api.model.Ticket;
import com.tickets.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.tickets.api.service.TicketService;
import com.tickets.api.repository.TicketRepository;

@Tag(name = "REST TicketController", description = "A  REST controller with the following endpoints:")
@RestController
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private TicketService ticketService;
	
	@Autowired
    private TicketRepository ticketRepository;

    @Operation(
      summary = "Recover all users."
    )
    @GetMapping("/users")
    public Iterable<Dbuser> getUsers() {
        return userService.getUsers();
    }
    
    @Operation(
      summary = "Retrieve tickets assigned to the user by is ID"
    )
	@GetMapping("/users/{id}/ticket")
	public Iterable<Ticket> findByUserId(@PathVariable("id") final Long id) {
		return ticketRepository.findByUserId(id);
	}
	
	@Operation(
      summary = "Create a user"
    )
	@PostMapping("/users")
	public Dbuser createUser(@RequestBody Dbuser user) {
		return userService.saveUser(user);
	}
	
	
	@Operation(
      summary = "Edit a user"
    )
	@PutMapping("/users/{id}")
	public Dbuser updateUser(@PathVariable("id") final Long id, @RequestBody Dbuser user) {
		Optional<Dbuser> e = userService.getUser(id);
		if(e.isPresent()) {
			Dbuser currentUser = e.get();
			
			String email = user.getEmail();
			if(email != null) {
				currentUser.setEmail(email);
			}
			
			String username = user.getUsername();
			if(username != null) {
				currentUser.setUsername(username);;
			}
			
			String password = user.getPassword();
			if(password != null) {
				currentUser.setPassword(password);;
			}
			
			userService.saveUser(currentUser);
			return currentUser;
		} else {
			return null;
		}
	}

}
