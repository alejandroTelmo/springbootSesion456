package com.sesion456.ejercicios.entity;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class Laptop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String model;
    String description;
    Integer ram;
    String processor;
    String hardDisk;
    String brand;

    public Laptop() {
    }

    public Laptop(String model, String description, Integer ram, String processor, String hardDisk, String brand) {
        this.model = model;
        this.description = description;
        this.ram = ram;
        this.processor = processor;
        this.hardDisk = hardDisk;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
