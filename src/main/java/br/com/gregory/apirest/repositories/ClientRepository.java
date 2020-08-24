package br.com.gregory.apirest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gregory.apirest.models.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Integer>{

	Page<ClientModel> findByName(String name, Pageable page);

}