import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int u, v;
    static ArrayList<Integer>[] list;
    static int[] apple;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 수
        k = Integer.parseInt(st.nextToken()); // k이하 노드에 있는 사과
        list = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list[u].add(v);
        }

        apple = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apple[i] = Integer.parseInt(st.nextToken());
        }

        cnt = dfs(0, 0); // 시작 노드, 깊이
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int start, int count) {
        if (count > k) return 0;
        int sum = apple[start];
        for (int next : list[start]) {
            sum += dfs(next, count+1);
        }
        return sum;
    }
}