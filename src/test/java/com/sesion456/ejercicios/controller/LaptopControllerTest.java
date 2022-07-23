package com.sesion456.ejercicios.controller;

import com.sesion456.ejercicios.dto.LaptopDTO;
import com.sesion456.ejercicios.service.ILaptopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

        private TestRestTemplate testRestTemplate;

        @Autowired
         private RestTemplateBuilder restTemplateBuilder;
        @Autowired
        ILaptopService laptopService;

        @LocalServerPort
        private int port;

        @BeforeEach
        void setUp() {
            restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
            testRestTemplate = new TestRestTemplate(restTemplateBuilder);
        }

        @DisplayName("Comprobar hola mundo desde controladores Spring REST")
        @Test
        void hello() {
            ResponseEntity<String> response  =
                    testRestTemplate.getForEntity("/saludo", String.class);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(200, response.getStatusCodeValue());
            assertEquals("Hola Open-Bootcamp !", response.getBody());
        }
    @DisplayName("Metodo crear una laptop de nuestra api")
    @Test
    void save() {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json= """
                {
                     "model":"testeando guardar",
                     "description": "Liviana, ultra delgada y super rápida.",
                     "ram":16,
                     "processor":"i7 11500",
                     "hardDisk":"480gb",
                     "brand":"uno"
                }
                """;
        HttpEntity<String> request=new HttpEntity<>(json,headers);
      ResponseEntity<LaptopDTO> response=  testRestTemplate.exchange("/laptops",HttpMethod.POST,request,LaptopDTO.class);
    LaptopDTO result=response.getBody();
    Optional<LaptopDTO> consulta=laptopService.getLaptop(1L);

      assertEquals(consulta.get().getBrand(),result.getBrand());
    }
        @DisplayName("Probamos agregar una laptop con otro metodo de comprobacion ")
        @Test
        void addLaptop(){
                LaptopDTO laptopDTO=new LaptopDTO("test","nuevo",77,"intel i7","480gb","nuevaAAA");
                ResponseEntity<LaptopDTO> response=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO,LaptopDTO.class);
             Optional<LaptopDTO> laptopDTO1=laptopService.getLaptop(1L);
                assertEquals(77,laptopDTO1.get().getRam());
        }

    @DisplayName("Comprobamos la busqueda por id")
    @Test
    void findOneById() {
        LaptopDTO laptopDTO=new LaptopDTO("test","nuevo",77,"intel i7","480gb","solloo");
        ResponseEntity<LaptopDTO> response=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO,LaptopDTO.class);
        Optional<LaptopDTO> laptopDTO1=laptopService.getLaptop(1L);
        LaptopDTO laptopDTO2=new LaptopDTO("test","nuevo",77,"intel i7","480gb","nuevaAAA");
        ResponseEntity<LaptopDTO> response1=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO2,LaptopDTO.class);
        Optional<LaptopDTO> laptopDTO3=laptopService.getLaptop(2L);
        assertEquals(laptopDTO1.get().getRam(),laptopDTO3.get().getRam());
        assertNotEquals(laptopDTO1.get().getBrand(),laptopDTO3.get().getBrand());
    }
    @DisplayName("Metodo buscar todas las laptops  de nuestra api")
    @Test
    void findAll() {
        LaptopDTO laptopDTO=new LaptopDTO("test","nuevo",77,"intel i7","480gb","solloo");
        ResponseEntity<LaptopDTO> response=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO,LaptopDTO.class);

        LaptopDTO laptopDTO2=new LaptopDTO("test","nuevo",77,"intel i7","480gb","nuevaAAA");
        ResponseEntity<LaptopDTO> response1=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO2,LaptopDTO.class);

        ResponseEntity<LaptopDTO[]>response3=testRestTemplate.getForEntity("/laptops",LaptopDTO[].class);

        assertEquals(2,response3.getBody().length);
        assertEquals(200,response3.getStatusCodeValue());

    }
    @DisplayName("Metodo actualizar de nuestra api")
    @Test
    void update() {
        LaptopDTO laptopDTO=new LaptopDTO("test","nuevo",77,"intel i7","480gb","nuevaAAA");
        ResponseEntity<LaptopDTO> response=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO,LaptopDTO.class);

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json= """
                {
                     "id":1,
                     "model":"testeando guardar",
                     "description": "Liviana, ultra delgada y super rápida.",
                     "ram":16,
                     "processor":"i7 11500",
                     "hardDisk":"480gb",
                     "brand":"uno"
                }
                """;
             HttpEntity<String> request=new HttpEntity<>(json,headers);
             testRestTemplate.exchange("/laptops/1", HttpMethod.PUT, request, Void.class);
             Optional<LaptopDTO> result=laptopService.getLaptop(1L);


                assertEquals("uno",result.get().getBrand());
    }
    @DisplayName("Comprobamos el metodo delete de nuestra api")
    @Test
    void delete() {
        LaptopDTO laptopDTO=new LaptopDTO("test","nuevo",77,"intel i7","480gb","nuevaAAA");
        ResponseEntity<LaptopDTO> response=testRestTemplate.postForEntity("http://localhost:"+port+"/laptops",laptopDTO,LaptopDTO.class);
        testRestTemplate.delete("/laptops/1");
        Optional<LaptopDTO> laptopDTO1=laptopService.getLaptop(1L);
        assertNull(laptopDTO1);

    }






}