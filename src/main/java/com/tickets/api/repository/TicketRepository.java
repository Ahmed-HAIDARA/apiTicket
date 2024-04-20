package com.tickets.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.api.model.Ticket;

import jakarta.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Iterable<Ticket> findByUserId(Long userID);
	
	@Transactional
	void deleteByUserId(long userId);
	
}
