package br.com.gregory.apirest.controllers.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.gregory.apirest.models.ClientModel;
import br.com.gregory.apirest.repositories.ClientRepository;

public class ClientForm {

	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	@Length(min = 11, max = 11)
	private String cpf;
	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ClientModel convert() {
		return new ClientModel(name, cpf, address);
	}

	public ClientModel update(Integer id, ClientRepository clientRepository) {
		ClientModel clientModel = clientRepository.getOne(id);
		clientModel.setName(this.name);
		clientModel.setCpf(this.cpf);
		clientModel.setAddress(this.address);
		return clientModel;
	}

}