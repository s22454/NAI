import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------------------------------");
        System.out.println("ROZPOCZYNANIE UCZENIA");
        System.out.println("---------------------------------------------");

        Network network = new Network(new File("./train"));
        System.out.println("KONIEC UCZENIA");

        try (Scanner scanner = new Scanner(System.in)) {
            String input = "";
            while (!input.equals("n")) {
                System.out.println("---------------------------------------------");
                System.out.print("Chcesz podac plik testowy? (y - tak; n - nie) Odp: ");
                input = scanner.nextLine();

                if (input.equals("y")) {
                    System.out.print("Podaj sciezke do pliku testowego: ");
                    String testPath = scanner.nextLine();

                    System.out.println("---------------------------------------------");
                    System.out.println("Podany plik jest w jezyku: " + network.test(testPath));
                }
            }

            System.out.println("---------------------------------------------");
            System.out.println("KONIEC TESTOWANIA PLIKOW");
            System.out.println("---------------------------------------------");
            System.out.println("TESTY UZYTKOWNIKA");

            input = "";
            while (!input.equals("n")){
                System.out.println("---------------------------------------------");
                System.out.print("Chcesz podac wlasny tekst? (y - tak; n - nie) Odp: ");
                input = scanner.nextLine();

                if (input.equals("y")){
                    System.out.print("Podaj tekst: ");
                    String userDataInput = scanner.nextLine();
                    System.out.println("Jezyk: " + network.userTest(userDataInput));
                }
            }

            System.out.println("---------------------------------------------");
            System.out.println("KONIEC PRACY!");
            System.out.println("---------------------------------------------");
        }
    }
}
