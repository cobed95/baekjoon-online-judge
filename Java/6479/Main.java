import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        LinkedList<String> outputs = new LinkedList<>();
        while (n != 0) {
            LinkedList<Integer> result = factorial(n);
            int[] book = countDigits(result);
            outputs.add(buildOutput(n, book));
            n = scanner.nextInt();
        }

        String output = outputs.stream().reduce((a, b) -> a + "\n" + b).get();
        
        System.out.println(output);
    }

    public static LinkedList<Integer> factorial(int n) {
        LinkedList<Integer> initial = new LinkedList<>();
        initial.add(1);

        if (n == 1) return initial;
        else return factorialDP(n, 2, initial);
    }

    public static LinkedList<Integer> factorialDP(int n, 
                                                  int idx, 
                                                  LinkedList<Integer> prev) {
        if (idx == n) 
            return multiply(prev, idx);
        else return factorialDP(n, idx + 1, multiply(prev, idx));
    }

    public static LinkedList<Integer> multiply(LinkedList<Integer> multiplicand,
                                               int multiplier) {
        for (int i = 0; i < multiplicand.size(); i++)
            multiplicand.set(i, multiplicand.get(i) * multiplier);
        
        smoothen(multiplicand);

        return multiplicand;
    }

    public static void smoothen(LinkedList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) 
            if (list.get(i) >= 10) {
                int overflow = list.get(i) / 10;
                list.set(i + 1, list.get(i + 1) + overflow);
                list.set(i, list.get(i) % (overflow * 10));
            }
        
        while (list.get(list.size() - 1) >= 10) {
            int overflow = list.get(list.size() - 1) / 10;
            list.set(list.size() - 1, list.get(list.size() - 1) % (overflow * 10));
            list.add(overflow);
        }
    }

    public static int[] countDigits(LinkedList<Integer> list) {
        int[] book = new int[10];
        list.forEach(digit -> book[digit]++);
        return book;
    }

    public static void testPrint(LinkedList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) 
            System.out.print(list.get(i) + " ");

        System.out.println();
    }

    public static String buildOutput(int n, int[] book) {
        String result = "";
        result += n + "! --\n";
        result += String.format("%6s%5s", "(" + 0 + ")", book[0]);
        for (int i = 1; i < 5; i++) 
            result += String.format("%7s%5s", "(" + i + ")", book[i]);

        result += "\n";
        
        result += String.format("%6s%5s", "(" + 5 + ")", book[5]);
        for (int i = 6; i < 10; i++) 
            result += String.format("%7s%5s", "(" + i + ")", book[i]);

        return result;
    }
}
