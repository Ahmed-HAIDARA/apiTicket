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

import com.tickets.api.model.Ticket;
import com.tickets.api.model.Dbuser;
import com.tickets.api.repository.TicketRepository;
import com.tickets.api.repository.UserRepository;
import com.tickets.api.service.TicketService;
import com.tickets.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "REST TicketController", description = "A  REST controller with the following endpoints:")
@RestController
public class TicketController {
	
	@Autowired
    private TicketService ticketService;
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private TicketRepository ticketRepository;
	
	@Operation(
      summary = "Collect all tickets."
    )
    @GetMapping("/tickets")
    public Iterable<Ticket> getTickets() {
        return ticketService.getTickets();
    }
    
    @Operation(
      summary = "Retrieve a ticket by its ID."
    )
	@GetMapping("/tickets/{id}")
	public Ticket getTicket(@PathVariable("id") final Long id) {
		Optional<Ticket> ticket = ticketService.getTicket(id);
		if(ticket.isPresent()) {
			return ticket.get();
		} else {
			return null;
		}
	}
	
    @Operation(
      summary = "Create a new ticket."
    )
	@PostMapping("/tickets")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		Long id = ticketRepository.count();
		Ticket currentTicket = ticket;		
		currentTicket.setId(id+1);	
		
		return ticketService.saveTicket(currentTicket);
	}

    @Operation(
      summary = "Update an existing ticket."
    )
	@PutMapping("/tickets/{id}")
	public Ticket updateTicket(@PathVariable("id") final Long id, @RequestBody Ticket ticket) {
		Optional<Ticket> e = ticketService.getTicket(id);
		if(e.isPresent()) {
			Ticket currentTicket = e.get();
			
			String title = ticket.getTitle();
			if(title != null) {
				currentTicket.setTitle(title);
			}
			
			String description = ticket.getDescription();
			if(description != null) {
				currentTicket.setDescription(description);;
			}
			
			String status = ticket.getStatus();
			if(status != null) {
				currentTicket.setStatus(status);
			}
			
			ticketService.saveTicket(currentTicket);
			return currentTicket;
		} else {
			return null;
		}
	}
	
    @Operation(
      summary = "Assign a ticket to a user."
    )
	@PutMapping("/tickets/{id}/assign/{useId}")
	public Ticket updateTicketAssign(@PathVariable("id") final Long id, @PathVariable("useId") final Long useId, @RequestBody Ticket ticket) {
		Optional<Dbuser> user = userService.getUser(useId);
		Optional<Ticket> e = ticketService.getTicket(id);
		if(user.isPresent()) {
			if(e.isPresent()) {
				Ticket currentTicket = e.get();
				
				Dbuser users = user.get();
				if(users != null) {
					currentTicket.setUser(users);
				}
				
				ticketService.saveTicket(currentTicket);
				return currentTicket;
			} else {
				return null;
			}
		} else {
			return null;
		}	
	}
	
    @Operation(
      summary = "Delete a ticket by its ID."
    )
	@DeleteMapping("/tickets/{id}")
	public Iterable<Ticket> deleteTicket(@PathVariable("id") final Long id) {
		ticketService.deleteTicket(id);
		return ticketService.getTickets();
	}

}
