package com.sesion456.ejercicios.service;

import com.sesion456.ejercicios.dto.LaptopDTO;

import java.util.List;
import java.util.Optional;

public interface ILaptopService {
    void save(LaptopDTO laptopDTO);
    void delete(Long id);
    void update(LaptopDTO laptopDTO);
    Optional<LaptopDTO> getLaptop(Long id);
    Optional<List<LaptopDTO>> allLaptop();
}
