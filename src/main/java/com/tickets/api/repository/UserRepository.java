package com.tickets.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.api.model.Dbuser;

@Repository
public interface UserRepository extends JpaRepository<Dbuser, Long> {

	Dbuser getById(Integer valueOf);
	
	Optional<Dbuser> findByUsername(String username);

}
