import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 자릿수

        int cnt = dfs(n, 1) + dfs(n, 2);

        System.out.println(cnt);

        br.close();
    }

    private static int dfs(int n, int cur) {
        if (n == 1) {
            return (cur%3 == 0) ? 1 : 0;
        }

        int total = 0;
        for (int i = 0; i <= 2; i++) {
            int nextNum= cur*10+i;
            total += dfs(n-1, nextNum);
        }

        return total;
    }
}