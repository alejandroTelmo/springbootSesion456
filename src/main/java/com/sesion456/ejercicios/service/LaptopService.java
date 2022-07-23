package com.sesion456.ejercicios.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesion456.ejercicios.dto.LaptopDTO;
import com.sesion456.ejercicios.entity.Laptop;
import com.sesion456.ejercicios.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LaptopService implements ILaptopService{

    final LaptopRepository laptopRepository;
    final ObjectMapper mapper;

    public LaptopService(LaptopRepository laptopRepository, ObjectMapper mapper) {
        this.laptopRepository = laptopRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(LaptopDTO laptopDTO) {
        laptopRepository.save(mapper.convertValue(laptopDTO, Laptop.class));
    }

    @Override
    public void delete(Long id) {
        if (laptopRepository.findById(id).isPresent())
            laptopRepository.deleteById(id);
    }

    @Override
    public void update(LaptopDTO laptopDTO) {
        if (laptopDTO.getId()!=null)
            laptopRepository.save(mapper.convertValue(laptopDTO,Laptop.class));
    }

    @Override
    public Optional<LaptopDTO> getLaptop(Long id) {
        Optional<LaptopDTO> laptopDTO=null;
       if (laptopRepository.findById(id).isPresent())
            laptopDTO=Optional.ofNullable(mapper.convertValue(laptopRepository.findById(id).get(),LaptopDTO.class));

        return laptopDTO;
    }

    @Override
    public Optional<List<LaptopDTO>> allLaptop() {
        List<Laptop> laptops=laptopRepository.findAll();
        List<LaptopDTO> laptopDTOS=new ArrayList<>();
        for (Laptop l:laptops){
            laptopDTOS.add(mapper.convertValue(l,LaptopDTO.class));
        }
        return Optional.ofNullable(laptopDTOS);
    }
    public void deleteAll(){
        laptopRepository.deleteAll();
    }
}
