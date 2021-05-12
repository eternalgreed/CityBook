package ru.abdullaev.service;

import ru.abdullaev.model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InOutService {

    private List<City> cityList = new ArrayList<>();
    private CityService cityService = new CityService();

    public List<City> getCityList() {
        return cityList;
    }

    public void start() throws FileNotFoundException {
        readToList("cities.txt");
        System.out.println("Модуль 1 - Неотсортированный список");
        cityService.printAll(cityList);
        System.out.println("Модуль 2 - Сортировка списка городов по наименованию в алфавитном порядке по убыванию ");
        cityService.sortByNameAndPrint(cityList);
        System.out.println("Модуль 2 - Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа в алфавитном порядке по убыванию ");
        cityService.sortByDistrictThanByNameAndPrint(cityList);
        System.out.println("Модуль 3 - Поиск города с наибольшим количеством жителей");
        String maxPopulationCity = cityService.maxPopulationCity(cityList);
        System.out.println(maxPopulationCity);
        System.out.println("Модуль 4- Поиск количества городов в разрезе регионов");
        cityService.countCitiesByRegionAndPrint(cityList);
    }

    public void readToList(String path) throws FileNotFoundException {
        File file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                String[] strings = line.split(";");
                cityList.add(createCity(strings));
            }
        }
    }

    public City createCity(String[] strings) {
        String name = strings[1];
        String region = strings[2];
        String district = strings[3];
        int population = Integer.parseInt(strings[4]);
        String foundation = strings[5];
        return new City(name, region, district, population, foundation);
    }
}
