import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();
    static int n;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if (n <= 10) {
            System.out.println(n);
            return;
        } else if (n >= 1023) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < 10; i++) {
            dfs(i);
        }
        Collections.sort(list);

        System.out.println(list.get(n));

        br.close();
    }

    private static void dfs(long num) {
        list.add(num);

        long modValue = num%10;
        if (modValue == 0) return;

        for (long i = modValue-1; i >= 0; i--) {
            long newValue = num*10+i;
            dfs(newValue);
        }
    }
}