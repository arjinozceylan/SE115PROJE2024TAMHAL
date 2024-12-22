import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class wayFinder {
    private countryMap countryMap;

    public wayFinder(countryMap countryMap) {
        this.countryMap = countryMap;
    }

    public void findShortestPath(String startCity, String endCity) {
        if (startCity.equals(endCity)) {
            System.out.println("The start and finish city is same");
            return;
        }

        City start = countryMap.getCityByName(startCity);
        City end = countryMap.getCityByName(endCity);

        if (start == null || end == null) {
            System.out.println("The start or finish city couldn't be found");
            return;
        }

        int cityCount = countryMap.getCityCount();
        int[] distances = new int[cityCount];
        boolean[] visited = new boolean[cityCount];
        int[] previous = new int[cityCount];

        for (int i = 0; i < cityCount; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;
        }

        distances[countryMap.getCityIndex(startCity)] = 0;

        for (int i = 0; i < cityCount; i++) {
            int nearestCityIndex = getNearestCity(distances, visited);
            visited[nearestCityIndex] = true;

            City nearestCity = countryMap.getCityByIndex(nearestCityIndex);
            if (nearestCity == null) continue;

            for (int j = 0; j < nearestCity.getConnectionCount(); j++) {
                City neighbor = nearestCity.getConnections()[j];
                int time = nearestCity.getTimes()[j];
                int neighborIndex = countryMap.getCityIndex(neighbor.getName());

                if (!visited[neighborIndex] && distances[nearestCityIndex] + time < distances[neighborIndex]) {
                    distances[neighborIndex] = distances[nearestCityIndex] + time;
                    previous[neighborIndex] = nearestCityIndex;
                }
            }
        }

        printPath(distances, previous, startCity, endCity);
    }

    private int getNearestCity(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex= -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minIndex= i;
            }
        }
        return minIndex;
    }

    private void printPath(int[] distances, int[] previous, String startCity, String endCity) {
        int endIndex = countryMap.getCityIndex(endCity);
        if (distances[endIndex] == Integer.MAX_VALUE) {
            System.out.println("There is no way between start and finish cities");
            return;
        }

        StringBuilder path = new StringBuilder();
        buildPathRecursive(previous, endIndex, path);
        System.out.println("Shortest way: " + path);
        System.out.println("Total time: " + distances[endIndex] + " minute");

        writeOutputToFile(path.toString(), distances[endIndex]);
    }

    private void buildPathRecursive(int[] previous, int index, StringBuilder path) {
        if (index == -1) return;
        buildPathRecursive(previous, previous[index], path);
        if (path.length() > 0) path.append(" -> ");
        path.append(countryMap.getCityByIndex(index).getName());
    }

    private void writeOutputToFile(String path, int totalTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Arjin/IdeaProjects/SE 115 PROJE 2024 TAM HAL/src/output.txt"))) {
            writer.write("Fastest Way: " + path + "\n");
            writer.write("Total Time: " + totalTime + " min\n");
            System.out.println("Outputs is wrote to the src/output.txt file");
        } catch (IOException e) {
            System.out.println("There is a problem in file writing: " + e.getMessage());
        }
    }



}
