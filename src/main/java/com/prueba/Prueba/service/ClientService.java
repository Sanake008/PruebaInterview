package com.prueba.Prueba.service;

import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;

import java.util.List;

public interface ClientService {
    public ClientDTO save(ClientDTO client)throws BadRequestException;
    public ClientDTO updateWhole(ClientDTO clientDTO) throws BadRequestException;
    public ClientDTO updateName(Integer id, String name) throws BadRequestException;
    public ClientDTO getClient(Integer id) throws NotFoundException;
    public List<ClientDTO> getAll();
    public void delete(Integer id)throws NotFoundException;
}
