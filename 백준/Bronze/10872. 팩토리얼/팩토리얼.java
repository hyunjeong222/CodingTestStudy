import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(factorial(n));

        br.close();
    }

    private static int factorial(int n) {
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans *= i;
        }

        return ans;
    }
}