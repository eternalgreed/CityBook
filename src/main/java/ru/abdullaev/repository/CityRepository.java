package ru.abdullaev.repository;

import ru.abdullaev.model.City;

import java.sql.*;
import java.util.*;

public class CityRepository {
    private final String url;
   /* private static Connection connection = null;
    private static ResultSet resultSet = null;
    private static Statement statement = null;*/
   /* private static String createQuery = "CREATE TABLE CITIES(\n" +
            " id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            " name VARCHAR(255) UNIQUE,\n" +
            " REGION VARCHAR(255),\n" +
            " DISTRICT VARCHAR(255),\n" +
            " population long,\n" +
            " foundation int\n" +
            ");";
    private static String insertQuery = "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Адыгейск', 'Адыгея', 'Южный', 12248, 1973);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Майкоп', 'Адыгея', 'Южный', 144246, 1857);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Горно-Алтайск', 'Алтай', 'Сибирский', 56928, 1830);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Алдан', 'Якутия', 'Дальневосточный', 21277, 1924);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Александровск-Сахалинский', 'Сахалинская область', 'Дальневосточный', 10613, 1869);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Амурск', 'Хабаровский край', 'Дальневосточный', 42977, 1958);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Абдулино', 'Оренбургская область', 'Приволжский', 20663, 1795);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Агидель', 'Башкортостан', 'Приволжский', 16365, 1980);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Агрыз', 'Татарстан', 'Приволжский', 19299, 1646);";*/

   /* public static boolean init() {

        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            statement.execute(createQuery);
            statement.execute(insertQuery);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }*/

    public CityRepository(String url) {
        this.url = url;
    }

    public  List<City> findAll() {
        List<City> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES");){
            while (resultSet.next()) {
                City city = new City(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6));
                list.add(city);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public  boolean addCity(City city) {
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION) VALUES ('" + city.getName() + "', '" + city.getRegion() + "', '" + city.getDistrict() + "', " + city.getPopulation() + ", " + city.getFoundation() + ") ;");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public  boolean deleteCity(String name) {
        String deleteQuery = "DELETE FROM CITIES WHERE NAME = '" + name + "' " + ";";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();) {
            if (statement.executeUpdate(deleteQuery) == 0) {
                return false;
            }
            ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean editCity(City city, String name) {
        /*
        UPDATE CITIES
        SET NAME = city.getName(),
        REGION = city.getRegion(),
        DISTRICT = city.getDistrict(),
        POPULATION = city.getPopulation(),
        FOUNDATION = city.getFoundation();
        WHERE NAME = name;
       */
        String updateQuery =  "UPDATE CITIES\n" +
                "        SET\n" +
                "        NAME = '" + city.getName() +"',\n" +
                "        REGION = '" + city.getRegion() +"',\n" +
                "        DISTRICT = '" + city.getDistrict() +"',\n" +
                "        POPULATION = " + city.getPopulation() + ",\n" +
                "        FOUNDATION = " + city.getFoundation() + "\n" +
                "        WHERE NAME = '" + name + "';";
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();) {
            return statement.executeUpdate(updateQuery) !=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Optional<City> findCity(String name) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES WHERE NAME = '" + name + "';")
             ) {
            resultSet.next();
            City city = new City(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6));
            resultSet.close();
            return Optional.of(city);
        } catch (SQLException throwables) {
            return Optional.empty();
        }
    }

    public List<City> findAllSortedByName(){
        List<City> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES ORDER BY LOWER(NAME) ASC")
        ){
            while (resultSet.next()) {
                City city = new City(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6));
                list.add(city);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<City> findAllSortedByDistrict(){
        List<City> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM CITIES ORDER BY LOWER(DISTRICT) ASC, LOWER(NAME) ASC")
        ){
            while (resultSet.next()) {
                City city = new City(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6));
                list.add(city);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public String maxPopulationCity(){
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, POPULATION FROM CITIES ORDER BY POPULATION DESC LIMIT 1;");
        )  {
            resultSet.next();
            String res = resultSet.getInt(1) + " - " + resultSet.getInt(2);
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public Map<String, Integer> countByRegions(){
        Map<String, Integer> map = new HashMap<>();
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  REGION, count(name) as quantity from CITIES group by region");
        )  {
            while (resultSet.next()){
                map.put(resultSet.getString("Region"), resultSet.getInt("quantity"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return map;
    }

}
