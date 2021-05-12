package ru.abdullaev;

import ru.abdullaev.service.InOutService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new InOutService().start();
    }
}