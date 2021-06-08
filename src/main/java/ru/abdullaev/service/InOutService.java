package ru.abdullaev.service;

import ru.abdullaev.model.City;
import ru.abdullaev.repository.CityRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InOutService {

    public void start() throws FileNotFoundException {
        List<City> cityList = readToList("cities.txt");
        CityService cityService = new CityService(cityList);
        /*System.out.println("Модуль 1 - Неотсортированный список");
        cityList.forEach(System.out::println);
        System.out.println("Модуль 2 - Сортировка списка городов по наименованию в алфавитном порядке по убыванию ");
        cityService.sortByName().forEach(System.out::println);
        System.out.println("Модуль 2 - Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа в алфавитном порядке по убыванию ");
        cityService.sortByDistrictThanByName().forEach(System.out::println);
        System.out.println("Модуль 3 - Поиск города с наибольшим количеством жителей");
        System.out.println(cityService.maxPopulationCity());
        System.out.println("Модуль 4- Поиск количества городов в разрезе регионов");
        cityService.countCitiesByRegion().forEach(System.out::println);*/
       // CityRepository.init();
        CityRepository cityRepository = new CityRepository("jdbc:h2:/Users/a19188821/IdeaProjects/CityBook/src/main/resources/cities;MV_STORE=false");
        Scanner scan = new Scanner(System.in);
        int x;
        String s = "";

        while (!"0".equals(s)) {
            System.out.println("\n1. Для вывода всех городов введите 1");
            System.out.println("2. Для сортировки городов по имени введите 2");
            System.out.println("3. Для сортировки городов по Федеральному округу и имени введите 3");
            System.out.println("4. Для поиска города с наибольшим количеством жителей 4");
            System.out.println("5. Для поиска количества городов в разрезе регионов введите 5");
            System.out.println("6. Для вывода списка городов из БД введите 6");
            System.out.println("7. Для добавления нового города в БД введите 7");
            System.out.println("8. Для изменения города в БД введитe 8");
            System.out.println("9. Для удаления города в БД введитe 9");
            System.out.println("9. Для поиска города по имени в БД введитe 9");
            System.out.println("11. Для сортировки по именив и выводу из БД введите 11");
            System.out.println("12. Для сортировки по федеральному округу и затем по имени и выводу из БД введите 12");
            System.out.println("13. Для вывода города с максимальным населением из БД введите 13");
            System.out.println("14. Для вывода информации с количеством городов по регионам из БД введите 14");
            System.out.println("0. Для выхода из приложения введие 0");
            s = scan.next();
            try {
                x = Integer.parseInt(s);
                switch (x) {
                    case 1:
                        cityList.forEach(System.out::println);
                        break;
                    case 2:
                        cityService.sortByName().forEach(System.out::println);
                        break;
                    case 3:
                        cityService.sortByDistrictThanByName().forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println(cityService.maxPopulationCity());
                        break;
                    case 5:
                        cityService.countCitiesByRegion().forEach(System.out::println);
                        break;
                    case 6:
                        cityRepository.findAll().forEach(System.out::println);
                        break;
                    case 7:
                        cityRepository.addCity(new City("SPB", "SPB", "LEN", 200000, "1730"));
                        cityRepository.findAll().forEach(System.out::println);
                        break;
                    case 8:
                        cityRepository.editCity(new City("SPB123",
                                "SPB", "LENIN",
                                200000, "1730"),
                                "SPB123");
                        cityRepository.findAll().forEach(System.out::println);
                        break;
                    case 9:
                        cityRepository.deleteCity("SPB123");
                        cityRepository.findAll().forEach(System.out::println);
                        break;
                    case 10:
                        System.out.println(cityRepository.findCity("SPB123").get());
                        break;
                    case 11:
                        cityRepository.findAllSortedByName().forEach(System.out::println);
                        break;
                    case 12:
                        cityRepository.findAllSortedByDistrict().forEach(System.out::println);
                        break;
                    case 13:
                        System.out.println(cityRepository.maxPopulationCity());
                        break;
                    case 14:
                        StringBuilder stringBuilder = new StringBuilder();
                        cityRepository.countByRegions().forEach((key, value) -> stringBuilder.append(key).append(" - ").append(value).append("\n"));
                        System.out.println(stringBuilder.toString().trim());
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
        System.out.println("До свидания!");
    }


    public List<City> readToList(String path) throws FileNotFoundException {
        File file = new File(path);
        List<City> cityList = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                String[] strings = line.split(";");
                cityList.add(createCity(strings));
            }
            return cityList;
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
