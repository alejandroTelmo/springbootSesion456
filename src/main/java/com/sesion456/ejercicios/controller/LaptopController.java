package com.sesion456.ejercicios.controller;

import com.sesion456.ejercicios.dto.LaptopDTO;
import com.sesion456.ejercicios.service.ILaptopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    final ILaptopService laptopService;

    public LaptopController(ILaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody LaptopDTO laptopDTO){
        laptopService.save(laptopDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public LaptopDTO findOneById(@PathVariable Long id){
        return laptopService.getLaptop(id).get();
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
}
