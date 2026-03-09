package lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    ArrayList<City> cities = new ArrayList<>();
    ArrayList<Country> countries = new ArrayList<>();

    public static void main(String[] args) {

        Main app = new Main();

        app.loadCities("Cities.csv");
        app.loadCountries("Countries.csv");
        app.highestCityPerCountry();
        app.mostPopulatedCountryPerContinent();
        app.highestPopulatedCapital();

    }

    public void loadCountries(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String code = data[0].trim();
                String name = data[1].trim();
                String continent = data[2].trim();
                double population = Double.parseDouble(data[4].trim());
                int  surfaceArea = Integer.parseInt(data[3].trim());
                double gnp = Double.parseDouble(data[5].trim());
                int capital = Integer.parseInt(data[6].trim());

                Country country = new Country(code, name, continent
                        , population, surfaceArea, gnp, capital);

                countries.add(country);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadCities(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int population = Integer.parseInt(data[2].trim());
                String countryCode = data[3].trim();

                City city = new City(id, name, population, countryCode);
                cities.add(city);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void highestCityPerCountry() {

        cities.stream()
                .collect(Collectors.groupingBy(
                        City::getCountryCode,
                        Collectors.maxBy(Comparator.comparingInt(City::getPopulation))
                ))
                .forEach((countryCode, cityOpt) ->
                        cityOpt.ifPresent(city ->
                                System.out.println(countryCode + " -> " + city)
                        )
                );
    }
    public void mostPopulatedCountryPerContinent() {

        countries.stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        Collectors.maxBy(Comparator.comparingInt(Country::getPopulation))
                ))
                .forEach((continent, countryOpt) ->
                        countryOpt.ifPresent(country ->
                                System.out.println(continent + " -> " + country)
                        )
                );
    }
    public void highestPopulatedCapital() {

        Set<Integer> capitalIds = countries.stream()
                .map(Country::getCapital)
                .collect(Collectors.toSet());

        cities.stream()
                .filter(city -> capitalIds.contains(city.getId()))
                .max(Comparator.comparingInt(City::getPopulation))
                .ifPresent(System.out::println);
    }

}