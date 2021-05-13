package ru.abdullaev;

import ru.abdullaev.model.City;
import ru.abdullaev.repository.CityRepository;
import ru.abdullaev.service.InOutService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // CityRepository.init();
        new InOutService().start();
    }
}