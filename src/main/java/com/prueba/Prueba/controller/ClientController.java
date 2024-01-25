package com.prueba.Prueba.controller;

import com.prueba.Prueba.dto.ClientDTO;
import com.prueba.Prueba.exceptions.BadRequestException;
import com.prueba.Prueba.exceptions.NotFoundException;
import com.prueba.Prueba.service.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ClientDTO create(@RequestBody ClientDTO clientDTO) throws BadRequestException {
        return clientService.save(clientDTO);
    }
    @PutMapping("/update")
    public ClientDTO update(@RequestBody ClientDTO clientDTO) throws BadRequestException {
        return clientService.updateWhole(clientDTO);
    }
    @GetMapping("/get")
    public ClientDTO getClientById(@RequestParam (name = "id") Integer id) throws NotFoundException {
        return clientService.getClient(id);
    }
    @GetMapping("/getAll")

    public List<ClientDTO> getAll(){
        return  clientService.getAll();
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam (name = "id") Integer id) throws NotFoundException {
        clientService.delete(id);
    }

}
