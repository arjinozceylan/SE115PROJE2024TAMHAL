import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class countryMap {
    private int cityCount;
    private City[] cities;

    public void loadMapFromFile(String fileName) throws IOException {
        int lineNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            lineNumber++;
            cityCount = Integer.parseInt(reader.readLine().trim());
            cities = new City[cityCount];


            lineNumber++;
            String[] cityNames = reader.readLine().trim().split(" ");
            for (int i = 0; i < cityCount; i++) {
                cities[i] = new City(cityNames[i]);
            }


            lineNumber++;
            int routeCount = Integer.parseInt(reader.readLine().trim());


            for (int i = 0; i < routeCount; i++) {
                lineNumber++;
                String[] routeInfo = reader.readLine().trim().split(" ");
                String city1 = routeInfo[0];
                String city2 = routeInfo[1];
                int time = Integer.parseInt(routeInfo[2]);

                City c1 = getCityByName(city1);
                City c2 = getCityByName(city2);
                if (c1 != null && c2 != null) {
                    c1.addConnection(c2, time);
                    c2.addConnection(c1, time);
                }
            }




        } catch (IOException e) {
            System.out.println("Error Line: " + lineNumber + ", error in file");
            throw e; // Hatayı üst seviyeye iletir
        } catch (NumberFormatException e) {
            System.out.println("Error Line: " + lineNumber + ", invalid number format");
            throw new IOException("Number format error at line " + lineNumber, e);
        }
    }

    public City getCityByName(String name) {
        for (City city : cities) {
            if (city != null && city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public int getCityCount() {
        return cityCount;
    }

    public City getCityByIndex(int index) {
        return cities[index];
    }

    public int getCityIndex(String name) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
