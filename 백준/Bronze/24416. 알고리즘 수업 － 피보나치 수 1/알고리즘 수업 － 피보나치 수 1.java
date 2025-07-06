import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int code1Cnt = 0, code2Cnt = 0;
    static int[] f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        f = new int[n+1];

        fib(n);
        fibonacci(n);

        StringBuilder sb = new StringBuilder();
        sb.append(code1Cnt).append(" ").append(code2Cnt).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static int fibonacci(int n) {
        f[1] = 1; f[2] = 1;
        for (int i = 3; i <= n; i++) {
            code2Cnt++;
            f[i] = f[i-1]+f[i-2];
        }
        return f[n-1];
    }

    private static int fib(int n) {
        if (n == 1 || n == 2) {
            code1Cnt++;
            return 1;
        }
        else return (fib(n-1)+fib(n-2));
    }
}