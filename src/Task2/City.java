package Task2;

import java.util.Map;
import java.util.Objects;

/**
 * City class represents a city in the graph.
 */
public class City {
    private int index;
    private String name;
    private int numberOfNeighbours;
    private Map<Integer, Integer> neighbours;

    public City(int index, String name, int numberOfNeighbours, Map<Integer, Integer> neighbours) {
        this.index = index;
        this.name = name;
        this.numberOfNeighbours = numberOfNeighbours;
        this.neighbours = neighbours;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNeighbours() {
        return numberOfNeighbours;
    }

    public void setNumberOfNeighbours(int numberOfNeighbours) {
        this.numberOfNeighbours = numberOfNeighbours;
    }

    public Map<Integer, Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Map<Integer, Integer> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return index == city.index && numberOfNeighbours == city.numberOfNeighbours && Objects.equals(name, city.name) && Objects.equals(neighbours, city.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, numberOfNeighbours, neighbours);
    }

    @Override
    public String toString() {
        return "City{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", numberOfNeighbours=" + numberOfNeighbours +
                ", neighbours=" + neighbours +
                '}';
    }
}
