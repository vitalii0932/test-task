package Task1;

import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select how do you want to input the data:");
        System.out.println("1. From console");
        System.out.println("2. From file (input.txt)");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Enter N:");
            int N = sc.nextInt();
            System.out.printf("Permutations count for N = %d: %d\n", N, getPermutationsCount(N));
        } else if (choice == 2) {
            try {
                IO io = new IO();
                System.out.println("Working Directory = " + System.getProperty("user.dir"));
                int N = io.readFile();

                int result = getPermutationsCount(N);

                System.out.printf("Permutations count for N = %d: %d\n", N, result);
                io.writeFile(result);
            } catch (Exception e) {
                System.out.println(String.format("An error occurred: %s", e.getMessage()));
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static int getPermutationsCount(int N) {
        if (N < 1) {
            System.out.println("N should be greater than 0");
            return -1;
        }

        if (N == 1) {
            return 1;
        }

        ArrayList<String> permutations = new ArrayList<>();

        generatePermutations(N, "", permutations, 0, 0);

//        for (var permutation : permutations) {
//            System.out.println(permutation);
//        }

        return permutations.size();
    }

    public static void generatePermutations(int N, String current, ArrayList<String> permutations, int open, int close) {
        if (current.length() == 2 * N) {
            permutations.add(current);
            return;
        }

        if (open < N) {
            generatePermutations(N, current + "(", permutations, open + 1, close);
        }

        if (close < open) {
            generatePermutations(N, current + ")", permutations, open, close + 1);
        }
    }
}
