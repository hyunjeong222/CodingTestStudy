import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean flag = false; // 조건에 만족하는 수를 찾았는지

        n -= 1; // n보다 크거나 같고 -> n부터 시작

        while (!flag) {
            n += 1;

            String num = String.valueOf(n);
            String reversed = new StringBuilder(num).reverse().toString();

            boolean isPrime = true; // 소수 여부

            if (num.equals(reversed)) { // 팰린드롬인 경우
                // 소수 판별

                if (n == 1) { // 1은 소수 X
                    isPrime = false;
                }

                // 소수 판별
                for (int i = 2; i <= Math.sqrt(n); i++) {
                    if (n % i == 0) { // 소수 X
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) { // 소수가 맞다면
                    flag = true; // 팰린드롬 & 소수 발견
                }
            }
        }

        System.out.println(n);

        br.close();
    }
}