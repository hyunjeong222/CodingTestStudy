import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr, len;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 자연수
        m = Integer.parseInt(st.nextToken()); // 길이

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        len = new int[m]; // 길이만큼 수열 만들기
        checked = new boolean[n];

        dfs(0, 0);

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int idx, int depth) {
        if (depth == m) {
            for (int i : len) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for (int i = idx; i < n; i++) {
            if (!checked[i] && arr[i] != before) {
                checked[i] = true;
                len[depth] = arr[i];
                before = arr[i];
                dfs(i, depth+1);
                checked[i] = false;
            }
        }
    }
}