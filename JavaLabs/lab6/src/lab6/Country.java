package lab6;

public class Country {
    private String code;
    private String name;
    private String continent;
    private double surfaceArea;
    private int population;
    private double gnp;
    private int capital;

    public Country(String code, String name, String continent,
                   double surfaceArea, int population,
                   double gnp, int capital) {

        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.gnp = gnp;
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public int getCapital() {
        return capital;
    }

    public String toString() {
        return code + " " + name + " " + continent + " " + population;
    }
}