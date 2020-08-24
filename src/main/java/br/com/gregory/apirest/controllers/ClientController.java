package br.com.gregory.apirest.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gregory.apirest.controllers.dto.ClientDto;
import br.com.gregory.apirest.controllers.forms.ClientForm;
import br.com.gregory.apirest.models.ClientModel;
import br.com.gregory.apirest.repositories.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public Page<ClientDto> list(@RequestParam(required = false) String name, @RequestParam int page,
			@RequestParam int size) {
		 Pageable pageable = PageRequest.of(page, size);
		
		if (name == null) {
			Page<ClientModel> clients = clientRepository.findAll(pageable);
			return ClientDto.convert(clients);
		} else {
			Page<ClientModel> clients = clientRepository.findByName(name, pageable);
			return ClientDto.convert(clients);
		}
	}

	@Transactional
	@PostMapping
	public ResponseEntity<ClientDto> register(@RequestBody @Valid ClientForm form, UriComponentsBuilder uriBuilder) {
		ClientModel client = form.convert();
		clientRepository.save(client);

		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClientDto(client));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> detail(@PathVariable Integer id) {
		Optional<ClientModel> optionalIdClient = clientRepository.findById(id);
		if (optionalIdClient.isPresent()) {
			return ResponseEntity.ok(new ClientDto(optionalIdClient.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ClientDto> update(@PathVariable Integer id, @RequestBody @Valid ClientForm form) {
		Optional<ClientModel> optionalIdClient = clientRepository.findById(id);
		if (optionalIdClient.isPresent()) {
			ClientModel clientModel = form.update(id, clientRepository);
			return ResponseEntity.ok(new ClientDto(clientModel));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<ClientModel> optionalIdClient = clientRepository.findById(id);
		if (optionalIdClient.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}