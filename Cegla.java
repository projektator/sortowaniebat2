import java.util.Scanner;

public class Cegla {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int c1 = scanner.nextInt(); //ilość cegiel 1-calowych
            int c5 = scanner.nextInt(); //ilość cegiel 5-calowych
            int d = scanner.nextInt(); //długość sciany
            while (d >= 5 && c5 > 0) {
                d -= 5;
                c5--;
            }
            while (d >= 1 && c1 > 0) {
                d--;
                c1--;
            }
            if (d == 0) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

}
