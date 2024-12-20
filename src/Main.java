import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        countryMap countryMap = new countryMap();


        System.out.println("Please enter the file name(example: map1.txt): ");
        String fileName = scanner.nextLine();


        try {
            countryMap.loadMapFromFile(fileName);
            System.out.println("File read is successful");
        } catch (IOException e) {
            System.out.println("There is a problem in file reading: " + e.getMessage());
            return;
        }


        System.out.println("Enter start city: ");
        String startCity = scanner.nextLine();

        System.out.println("Enter finish city: ");
        String endCity = scanner.nextLine();


        wayFinder wayFinder = new wayFinder(countryMap);
        wayFinder.findShortestPath(startCity, endCity);
    }
}
