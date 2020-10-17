package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {}
