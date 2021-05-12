package ru.abdullaev.service;

import ru.abdullaev.model.City;

import java.util.*;

public class CityService {

    private List<City> list;

    public CityService(List<City> list) {
        this.list = list;
    }

    public List<City> sortByName(){
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        return cities;
    }

    public List<City> sortByDistrictThanByName(){
        List<City> cities = new ArrayList<>(list);
        cities.sort(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName));
        return cities;
    }

    public String maxPopulationCity(){
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

    public List<String> countCitiesByRegion(){
        Map<String, Integer> hashMap = new HashMap<>();
        for (City city: list) {
            Integer count = hashMap.get(city.getRegion());
            if (count ==null) {
                hashMap.put(city.getRegion(),1);
            } else {
                hashMap.put(city.getRegion(),count+1);
            }
        }
        List<String> citiesByRegion = new ArrayList<>();
        Set<String> strings = hashMap.keySet();
        for (String region: strings) {
            citiesByRegion.add(region + " - " + hashMap.get(region));
        }
        return citiesByRegion;
    }


}
