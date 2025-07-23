import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        while (true) {
            if (isPalindrome(n) && isPrime(n)) {
                System.out.println(n);
                break;
            }

            n += 1;
        }

        br.close();
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false; // 0, 1은 소수 X

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    private static boolean isPalindrome(int n) {
        String num = String.valueOf(n);
        String reversed = new StringBuilder(num).reverse().toString();

        if (num.equals(reversed)) {
            return true;
        }

        return false;
    }
}