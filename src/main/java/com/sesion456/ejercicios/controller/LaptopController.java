package com.sesion456.ejercicios.controller;

import com.sesion456.ejercicios.dto.LaptopDTO;
import com.sesion456.ejercicios.service.ILaptopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    final ILaptopService laptopService;
    final Logger logger= LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(ILaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping
    public ResponseEntity<LaptopDTO> saveLaptop(@RequestBody LaptopDTO laptopDTO){
        if (laptopDTO.getId()!=null){
            logger.warn("El objeto tiene id, revisar.");
            return ResponseEntity.badRequest().build();
        }else
            laptopService.save(laptopDTO);

        return new ResponseEntity<LaptopDTO>(laptopDTO,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<LaptopDTO> findOneById(@PathVariable Long id){

        return laptopService.getLaptop(id);
    }
    @GetMapping
    public List<LaptopDTO> findAll(){
        return laptopService.allLaptop().get();
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody LaptopDTO laptopDTO){
        laptopService.update(laptopDTO);
        return  ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        laptopService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll(){
        laptopService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
