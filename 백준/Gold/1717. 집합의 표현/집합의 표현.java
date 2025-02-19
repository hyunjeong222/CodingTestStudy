import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        int command, a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            command = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else {
                sb.append(isSameParent(a, b) ? "YES" : "NO").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean isSameParent(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return true;
        return false;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) parents[b] = a;
            else parents[a] = b;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}