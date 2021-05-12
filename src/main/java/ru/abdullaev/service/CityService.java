package ru.abdullaev.service;

import ru.abdullaev.model.City;

import java.util.*;

public class CityService {
    public void printAll(List<City> list){
        list.forEach(System.out::println);
    }

    public void sortByNameAndPrint(List<City> list){
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        cities.forEach(System.out::println);
    }

    public void sortByDistrictThanByNameAndPrint(List<City> list){
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName));
        cities.forEach(System.out::println);
    }

    public String maxPopulationCity(List<City> list){
        int maxPopulation = 0;
        int maxIndex =0;
        for (int i = 0; i < list.size(); i++) {
            City city = list.get(i);
            if (city.getPopulation() > maxPopulation) {
                maxPopulation = city.getPopulation();
                maxIndex = i;
            }
        }
        return String.format("[%d] = %,d", maxIndex, maxPopulation);
    }

    public void countCitiesByRegionAndPrint(List<City> list){
        Map<String, Integer> hashMap = new HashMap<>();
        for (City city: list) {
            Integer count = hashMap.get(city.getRegion());
            if (count ==null) {
                hashMap.put(city.getRegion(),1);
            } else {
                hashMap.put(city.getRegion(),count+1);
            }
        }
        Set<String> strings = hashMap.keySet();
        for (String region: strings) {
            System.out.println(region + " - " +hashMap.get(region));
        }
    }


}
