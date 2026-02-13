import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, f;
    static int[] num;
    static boolean[] checked;
    static boolean flag;
    static StringBuilder sb  = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가장 윗줄에 있는 숫자의 개수
        f = Integer.parseInt(st.nextToken()); // 가장 밑에 줄에 있는 수

        num = new int[n];
        checked = new boolean[n+1];

        dfs(0);

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int depth) {
        if (flag) return;

        if (depth == n) {
            if (check(num, n)) {
                for (int i = 0; i < n; i++) {
                    sb.append(num[i]).append(" ");
                }
                flag = true;
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!checked[i]) {
                num[depth] = i;
                checked[i] = true;
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }

    private static boolean check(int[] num, int len) {
        int[] tmp = new int[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = num[i];
        }

        for (int i = len-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                tmp[j] = tmp[j]+tmp[j+1];
            }
        }

        return tmp[0] == f;
    }
}