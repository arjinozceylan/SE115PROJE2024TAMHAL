import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        countryMap countryMap = new countryMap();


        System.out.println("Lütfen giriş dosyasının adını girin (örneğin: map1.txt): ");
        String fileName = scanner.nextLine();


        try {
            countryMap.loadMapFromFile(fileName);
            System.out.println("File read is successful!");
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
            return;
        }


        System.out.println("Başlangıç şehrini girin: ");
        String startCity = scanner.nextLine();

        System.out.println("Bitiş şehrini girin: ");
        String endCity = scanner.nextLine();


        wayFinder wayFinder = new wayFinder(countryMap);
        wayFinder.findShortestPath(startCity, endCity);
    }
}
