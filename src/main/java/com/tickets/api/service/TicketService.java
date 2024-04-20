package com.tickets.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tickets.api.model.Ticket;
import com.tickets.api.model.Dbuser;
import com.tickets.api.repository.TicketRepository;

import lombok.Data;

@Data
@Service
public class TicketService {
	
	@Autowired
    private TicketRepository ticketRepository;
	
	public Iterable<Ticket> getTickets() {
        return ticketRepository.findAll();
    }
	
	public Ticket saveTicket(Ticket ticket) {
		Ticket saveTicket = ticketRepository.save(ticket);
        return saveTicket;
    }

    public Optional<Ticket> getTicket(final Long id) {
        return ticketRepository.findById(id);
    }
    
    public void deleteTicket(final Long id) {
    	ticketRepository.deleteById(id);
	}

}
