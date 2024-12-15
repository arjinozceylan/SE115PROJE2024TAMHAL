import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        countryMap countryMap = new countryMap();

        // Kullanıcıdan dosya adı al
        System.out.println("Lütfen giriş dosyasının adını girin (örneğin: map1.txt): ");
        String fileName = scanner.nextLine();

        // Dosya yükle ve hata durumunu yakala
        try {
            countryMap.loadMapFromFile(fileName);
            System.out.println("File read is successful!");
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
            return;
        }

        // Başlangıç ve bitiş şehirlerini al
        System.out.println("Başlangıç şehrini girin: ");
        String startCity = scanner.nextLine();

        System.out.println("Bitiş şehrini girin: ");
        String endCity = scanner.nextLine();

        // En kısa yolu hesapla
        wayFinder wayFinder = new wayFinder(countryMap);
        wayFinder.findShortestPath(startCity, endCity);
    }
}
