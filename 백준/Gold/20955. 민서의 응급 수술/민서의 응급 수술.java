import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 뉴런의 개수
        int m = Integer.parseInt(st.nextToken()); // 시냅스의 개수

        parents = new int[n+1];
        Arrays.fill(parents, -1);

        int ans = 0; // 최소 연산 횟수
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            // 뉴런의 번호
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (!union(u,v)) ans++;
        }

        for (int i = 1; i <= n; i++) {
            if (parents[i] < 0) ans++;
        }
        bw.append(--ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean union(int x, int y) {
        x = find(x); // x의 부모 노드 찾기
        y = find(y); // y의 부모 노드 찾기

        // 같은 그래프에 속해있다면
        if (x == y) return false;

        int h = 0;
        int l = 0;
        if (parents[x] < parents[y]) h = x;
        else h = y;
        if (parents[x] < parents[y]) l = y;
        else l = x;

        parents[h] += parents[l];
        parents[l] = h;

        return true;
    }

    private static int find(int x) {
        if (parents[x] < 0) return x;
        return parents[x] = find(parents[x]);
    }
}