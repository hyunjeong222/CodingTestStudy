import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int minNum = Integer.MAX_VALUE, num;
    static int[] arr, combi;
    static boolean[] checked;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String x = br.readLine();
        num = Integer.parseInt(x);

        size = x.length();

        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(x.split("")[i]);
        }

        checked = new boolean[size];
        combi = new int[size];
        dfs(0);

        System.out.println(minNum == Integer.MAX_VALUE ? 0 : minNum);
    }

    private static void dfs(int depth) {
        if (depth == size) {
            String s = "";
            for (int i : combi) {
                s += i;
            }
            int n = Integer.parseInt(s);

            if (num < n) {
                minNum = Math.min(minNum, n);
            }

            return;
        }

        for (int i = 0; i < size; i++) {
            if (!checked[i]) {
                checked[i] = true;
                combi[depth] = arr[i];
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }
}