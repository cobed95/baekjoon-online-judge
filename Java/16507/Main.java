import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(" ");
        int r = Integer.parseInt(split[0]);
        int c = Integer.parseInt(split[1]);
        int q = Integer.parseInt(split[2]);
        int[][] picture = new int[r][];
        for (int i = 0; i < r; i++) {
            picture[i] = new int[c];
        }

        fillArray(scanner, r, c, picture);
               
        int[][] corners = new int[q][];
        for (int i = 0; i < q; i++) {
            corners[i] = new int[4];
        }

        fillArray(scanner, q, 4, corners);

        for (int i = 0; i < q; i++) {
            printAverage(picture, corners[i]);
        }
    }

    public static void fillArray(Scanner scanner, int rows, int columns, int[][] array) {
        for (int i = 0; i < rows; i++) {
            String row = scanner.nextLine();
            String[] split = row.split(" ");
            for (int j = 0; j < columns; j++) {
                array[i][j] = Integer.parseInt(split[j]);
            }
        }
    }

    public static void printAverage(int[][] picture, int[] corners) {
        int sum = 0;
        for (int i = corners[0] - 1; i < corners[2]; i++) {
            for (int j = corners[1] - 1; j < corners[3]; j++) {
                sum += picture[i][j];
            }
        }

        int num = (corners[2] - corners[0] + 1) * (corners[3] - corners[1] + 1);

        System.out.println(sum / num);
    }
}
