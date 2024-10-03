import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parents;
    static int[] planets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n+1];
        planets = new int[n+1]; // 각 은하 내에 존재하는 행성들의 수
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            planets[i] = Integer.parseInt(br.readLine());
        }

        int a, b;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            union(a, b);
            sb.append(planets[find(a)]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) {
                parents[a] = b;
                planets[b] += planets[a];
            } else {
                parents[b] = a;
                planets[a] += planets[b];
            }
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}