package ru.abdullaev;

import ru.abdullaev.service.InOutService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

      /*  File file = new File("cities.txt");
        Scanner sc = new Scanner(file);
        List<City> cities = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            String[] strings = line.split(";");
            City city = new City(strings[1],strings[2],strings[3],Integer.parseInt(strings[4]),strings[5]);
            cities.add(city);
            System.out.println(city);
        }
        City [] notSorted = new City[cities.size()];
        notSorted = cities.toArray(notSorted);

        cities.sort(Comparator.comparing(o -> o.getName().toLowerCase()));

        System.out.println();
        System.out.println("========Sorted==========");
        System.out.println();
        for (City city : cities) {
            System.out.println(city);
        }

        System.out.println("========Sorted==========");*/

       /* cities.sort(Comparator.comparing(City::getDistrict)
                .thenComparing(City::getName));

        cities.sort(Comparator.comparing((City city1) -> city1.getDistrict())
                .thenComparing(city2 -> city2.getName()));
        */
        /*cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getDistrict().compareTo(o2.getDistrict());
            }
        }.thenComparing(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }));*/
      /*  for (City city : cities) {
            System.out.println(city);
        }

        int maxPopulation = 0;
        int index =0;
        int maxIndex =0;
        for (City city: notSorted) {
            index++;
            if (city.getPopulation() > maxPopulation){
                maxPopulation = city.getPopulation();
                maxIndex = index;
            }
        }
        System.out.printf("[%d] = %,d \n", maxIndex, maxPopulation);*/
       /* Map<String, Integer> hashMap = new HashMap<>();
        for (City city: notSorted) {
            Integer count = hashMap.get(city.getRegion());
            if (count ==null) {
                hashMap.put(city.getRegion(),1);
            } else {
                hashMap.put(city.getRegion(),count+1);
            }
        }
        Set<String> strings = hashMap.keySet();
        for (String region: strings) {
            System.out.println(region + "- " +hashMap.get(region));
        }*/
        new InOutService().start();

    }
}