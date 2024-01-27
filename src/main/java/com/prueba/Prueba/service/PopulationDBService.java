package com.prueba.Prueba.service;

import com.prueba.Prueba.dto.DBData;
import com.prueba.Prueba.exceptions.BadRequestException;

public interface PopulationDBService {

    public String populate(DBData dbData) throws BadRequestException;

}
