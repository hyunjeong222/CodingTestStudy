import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] len;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 자연수
        m = Integer.parseInt(st.nextToken()); // 길이

        len = new int[m]; // 길이만큼 수열 만들기
        checked = new boolean[n];

        dfs(0);

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int depth) {
        if (depth == m) {
            for (int i : len) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                len[depth] = i+1;
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }
}