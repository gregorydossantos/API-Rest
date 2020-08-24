package br.com.gregory.apirest.controllers.dto;

import org.springframework.data.domain.Page;

import br.com.gregory.apirest.models.ClientModel;

public class ClientDto {
	
	private Integer id;
	private String name;
	private String cpf;
	private String address;
	
	public ClientDto(ClientModel client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.address = client.getAddress();
	}
	
	public Integer getId() {
		return id;
	}

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

	public static Page<ClientDto> convert(Page<ClientModel> clientList) {
		return clientList.map(ClientDto::new);
	}

}