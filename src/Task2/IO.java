package Task2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * IO class is responsible for reading the input file and returning the data in a structured way.
 * It reads the input file and returns the number of tests, number of cities, list of cities and list of edges.
 */
public class IO {
    // Class to represent a data from file
    public record IOResult(int testCount, int cityCount, List<City> cities, List<Edge> edges) {}

    private static final String inputFile = "input.txt"; // Path to the input file
    private static final String outputFile = "output.txt"; // Path to the output file

    /**
     * Read the input file
     *
     * @return - IOResult object containing the data from the file
     * @throws Exception - if there is an error while reading the file
     */
    public IOResult readFile() throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src").resolve("Task2").resolve(inputFile), StandardCharsets.UTF_8); // get all lines from the file

            if (lines.size() < 9) {
                throw new Exception("Invalid input file. Input file should contain at least 9 lines.");
            }

            int s = Integer.parseInt(lines.get(0)); // number of tests
            int n = Integer.parseInt(lines.get(1)); // number of cities

            List<City> cities = new ArrayList<>();
            int i = 2;
            for (int j = 0; j < n; j++) {
                int index = j + 1;
                String name = lines.get(i);

                if (name.isBlank() || name.isEmpty()) {
                    throw new Exception("Invalid input file. City name cannot be empty.");
                }

                i++;
                int numberOfNeighbours = Integer.parseInt(lines.get(i));
                i++;
                Map<Integer, Integer> neighbours = new HashMap<>();
                for (int k = 0; k < numberOfNeighbours; k++) {
                    String[] neighbour = lines.get(i).split(" ");
                    i++;
                    neighbours.put(Integer.parseInt(neighbour[0]), Integer.parseInt(neighbour[1]));
                }
                cities.add(new City(index, name, numberOfNeighbours, neighbours));
            }

            List<Edge> edges = new ArrayList<>();

            int r = Integer.parseInt(lines.get(i));
            i++;
            for (int j = 0; j < r; j++) {
                String[] edge = lines.get(i).split(" ");
                String source = edge[0];
                String destination = edge[1];

                if (source.isBlank() || source.isEmpty() || destination.isBlank() || destination.isEmpty()) {
                    throw new Exception("Invalid input file. Source and destination of an edge cannot be empty.");
                }

                edges.add(new Edge(source, destination));

                i++;
            }

            return new IOResult(s, n, cities, edges);
        } catch (Exception e) {
            throw new Exception(String.format("Error while reading the file: %s", e.getMessage()));
        }
    }

    /**
     * Writes the result to the output file
     *
     * @param result the result to write
     * @throws Exception if an error occurred while writing the file
     */
    public void writeFile(int[] result) throws Exception {
        try {
            // initialize a StringBuilder to store the result
            StringBuilder sb = new StringBuilder();
            for (int i : result) {
                // append the result to the StringBuilder
                sb.append(i).append("\n");
            }

            // write the result to the output file
            Files.writeString(Paths.get("src").resolve("Task2").resolve(outputFile), String.valueOf(sb.toString()));
        } catch (Exception e) {
            throw new Exception(String.format("Error while writing the file: %s", e.getMessage()));
        }
    }
}
