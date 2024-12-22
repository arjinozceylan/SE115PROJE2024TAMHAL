import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Error: Please provide the file name, start city, and end city as command-line arguments.");
            System.out.println("Usage: java -jar SE115Project.jar <filename> <startCity> <endCity>");
            return;
        }

        String fileName = args[0];
        String startCity = args[1];
        String endCity = args[2];


        countryMap countryMap = new countryMap();


        try {
            countryMap.loadMapFromFile(fileName);
            System.out.println("File read is successful!");
        } catch (IOException e) {
            System.out.println("Error: Could not read the file. " + e.getMessage());
            return;
        }


        wayFinder wayFinder = new wayFinder(countryMap);
        wayFinder.findShortestPath(startCity, endCity);
    }
}
