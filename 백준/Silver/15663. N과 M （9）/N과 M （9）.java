import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] combi;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        combi = new int[m];
        checked = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if (depth == m) {
            for (int val : combi) {
                sb.append(val + " ");
            }
            sb.append("\n");
            return;
        }

        int before = 0; // 이전에 뽑았던 값 저장
        for (int i = 0; i < n; i++) {
            if (checked[i]) {
                continue;
            } else if (before != arr[i]) {
                checked[i] = true;
                combi[depth] = arr[i];
                before = arr[i];
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }
}