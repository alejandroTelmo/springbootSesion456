package com.sesion456.ejercicios;

import com.sesion456.ejercicios.dto.LaptopDTO;
import com.sesion456.ejercicios.service.ILaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EjerciciosApplication {

	public static void main(String[] args) {

	ApplicationContext context= SpringApplication.run(EjerciciosApplication.class, args);
	 ILaptopService laptopService= context.getBean(ILaptopService.class);
		LaptopDTO laptopDTO=new LaptopDTO("nuevo","Hermosa pc",16,"AMD RIZEN","HDD solid 480GB","ChinoChan");
		LaptopDTO laptopDTO1=new LaptopDTO("otro","Gran pc",32,"Intel I9 10000","HDD solid 480GB","EEUU");
		laptopService.save(laptopDTO);
		laptopService.save(laptopDTO1);
	}


}
