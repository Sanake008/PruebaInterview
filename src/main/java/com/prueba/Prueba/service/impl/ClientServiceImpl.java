package com.prueba.Prueba.service.impl;

import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.entity.Client;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.repository.ClientRepository;
import com.prueba.Prueba.service.ClientService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder encoder;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) throws BadRequestException {
        Optional<Client> clientOptional = clientRepository.findById(clientDTO.getId());
        if(clientOptional.isPresent()){
            throw  new BadRequestException("Client already Exist!!");
        }
        Client client = modelMapper.map(clientDTO, Client.class);
        client.setPassword(encoder.encode(client.getPassword()));
        clientRepository.save(client);
        return clientDTO;
    }

    @Override
    public ClientDTO updateWhole(ClientDTO clientDTO) throws BadRequestException {
        Optional<Client> clientOptional = clientRepository.findById(clientDTO.getId());
        if(clientOptional.isEmpty()){
            throw  new BadRequestException("Client Not found");
        }
        Client client = modelMapper.map(clientDTO, Client.class);
        clientRepository.save(client);
        return clientDTO;
    }

    @Override
    public ClientDTO updateName(Integer id, String name) throws BadRequestException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()){
            throw  new BadRequestException("Client Not found");
        }
        Client client = clientOptional.get();
        client.setName(name);
        clientRepository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public ClientDTO getClient(Integer id) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()){
            throw  new NotFoundException("Client Not found");
        }
        return modelMapper.map(clientOptional.get(), ClientDTO.class);
    }

    @Override
    public List<ClientDTO> getAll() {
        return clientRepository.findAll().stream().map(c->ClientDTO.builder()
                .id(c.getId())
                .phone(c.getPhone())
                .name(c.getName())
                .address(c.getAddress())
                .lastName(c.getLastName())
                .mail(c.getMail())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()){
            throw  new NotFoundException("Client Not found");
        }
        clientRepository.deleteById(id);
        log.info("Client deleted!!");
    }
}
