package Task1;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * IO class for reading and writing files
 */
public class IO {
    private static final String inputFile = "input.txt"; // Path to the input file
    private static final String outputFile = "output.txt"; // Path to the output file

    /**
     * Reads the input file and returns the value of N
     *
     * @return N
     * @throws Exception if an error occurred while reading the file
     */
    public int readFile() throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src").resolve("Task1").resolve(inputFile), StandardCharsets.UTF_8); // get all lines from the file

            // check if the file contains only one line
            if (lines.size() != 1) {
                throw new Exception("Input file should contain only one line");
            }

            // parse the value of N
            int N = Integer.parseInt(lines.get(0));

            // check if N is greater than 0
            if (N < 1) {
                throw new Exception("N should be greater than 0");
            }

            // return the value of N
            return N;
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
    public void writeFile(int result) throws Exception {
        try {
            Files.writeString(Paths.get("src").resolve("Task1").resolve(outputFile), String.valueOf(result));
        } catch (Exception e) {
            throw new Exception(String.format("Error while writing the file: %s", e.getMessage()));
        }
    }
}
