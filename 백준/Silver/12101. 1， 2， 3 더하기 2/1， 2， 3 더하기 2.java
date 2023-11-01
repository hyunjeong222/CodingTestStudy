import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static ArrayList<String> list = new ArrayList<>(); // 만든 식을 담을 list
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 1, 2, 3으로 만들어야 하는 숫자
        k = Integer.parseInt(st.nextToken()); // n을 만드는 방법 중 몇번째인지

        dfs(0, ""); // 만든 숫자, 만드는 방법
        if (cnt != k) System.out.println(-1);
    }

    private static void dfs(int num, String str) {
        if (num == n) {
            cnt++;
            list.add(str.substring(1));
            if (cnt == k) {
                System.out.println(list.get(k-1));
                System.exit(0);
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            int next = num + i;
            if (next > n) continue;
            dfs(next, str + "+" + i);
        }
    }
}