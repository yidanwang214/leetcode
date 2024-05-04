import java.util.Scanner;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] array;
        while (input.hasNextLine()) {
            array = input.nextLine().toCharArray();
            System.out.println(Math.abs(array[0] - array[1]));
        }

    }
}
