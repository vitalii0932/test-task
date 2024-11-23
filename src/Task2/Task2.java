package Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Task2 solve
 */
public class Task2 {
    /**
     * Main method
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        IO io = new IO();
        try {
            IO.IOResult result = io.readFile(); // read data from file
            /* print data */
            System.out.println("Test count: " + result.testCount());
            System.out.println("City count: " + result.cityCount());
            System.out.println("Cities: ");
            for (City city : result.cities()) {
                System.out.println(city);
            }
            System.out.println("Edges: ");
            for (Edge edge : result.edges()) {
                System.out.println(edge);
            }

            /* get shortest path */
            int[] paths = new int[result.edges().size()];
            int i = 0;
            for (var edge : result.edges()) {
                int shortestPath = getShortestPath(edge, result.cities());
                System.out.println("Shortest path from " + edge.getSource() + " to " + edge.getDestination() + " is " + shortestPath);
                paths[i++] = shortestPath;
            }

            // write result to file
            io.writeFile(paths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get shortest path between two cities
     *
     * @param edge - edge between two cities
     * @param cities - the list of cities
     * @return shortest path
     */
    public static int getShortestPath(Edge edge, List<City> cities) {
        int source = -1;
        int destination = -1;

        // get source and destination city indexes
        for (City city : cities) {
            if (city.getName().equals(edge.getSource())) {
                source = city.getIndex();
            }
            if (city.getName().equals(edge.getDestination())) {
                destination = city.getIndex();
            }
        }

        if (source == -1 || destination == -1) {
            throw new IllegalArgumentException("Source or destination city not found in the list.");
        }

        // search for the shortest path
        List<Integer> paths = getPaths(source, destination, cities, new ArrayList<>(), 0, new boolean[cities.size()]);
        return paths.stream().mapToInt(Integer::intValue).min().orElse(-1);
    }

    /**
     * get paths between two cities
     *
     * @param source - source city index
     * @param destination - destination city index
     * @param cities - the list of cities
     * @param paths - list of paths
     * @param cost - current cost
     * @param visited - visited cities
     * @return - list of paths costs
     */
    public static List<Integer> getPaths(int source, int destination, List<City> cities, List<Integer> paths, int cost, boolean[] visited) {
        if (source == destination) {
            paths.add(cost);
            return paths;
        }

        // set current city as visited
        visited[source - 1] = true;
        City city = cities.get(source - 1);

        // go through all neighbours
        for (Map.Entry<Integer, Integer> neighbour : city.getNeighbours().entrySet()) {
            int neighbourIndex = neighbour.getKey();
            int newCost = cost + neighbour.getValue();

            // if neighbour is not visited, then go to it
            if (!visited[neighbourIndex - 1]) {
                getPaths(neighbourIndex, destination, cities, paths, newCost, visited);
            }
        }

        // reset current city as not visited
        visited[source - 1] = false;

        return paths;
    }
}
