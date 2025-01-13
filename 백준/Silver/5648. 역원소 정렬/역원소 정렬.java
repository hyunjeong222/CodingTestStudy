import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        long num;
        for (int i = 0; i < n; i++) {
            num = sc.nextLong();
            arr[i] = getReverse(num);
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (long i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static long getReverse(long num) {
        long reverse = 0;
        while (num != 0) {
            long dight = num % 10;
            reverse = reverse * 10 + dight;
            num /= 10;
        }
        return reverse;
    }
}