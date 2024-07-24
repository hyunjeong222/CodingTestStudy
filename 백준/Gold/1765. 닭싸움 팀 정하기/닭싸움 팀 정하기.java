import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 학생 수
        int m = Integer.parseInt(br.readLine()); // 인간관계

        parent = new int[n+1];
        ArrayList<ArrayList<Integer>> enemy = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            enemy.add(new ArrayList<>());
            parent[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (type == 'E') { // 원수
                enemy.get(p).add(q);
                enemy.get(q).add(p);
            } else { // F : 친구
                union(p, q);
            }
        }

        // 원수의 원수는 친구
        for (int i = 1; i <= n; i++) {
            for (int e : enemy.get(i)) {
                for (int f : enemy.get(e)) {
                    union(i, f);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}