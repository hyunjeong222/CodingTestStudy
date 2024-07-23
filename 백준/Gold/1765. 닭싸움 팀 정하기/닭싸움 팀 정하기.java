import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static ArrayList<ArrayList<Integer>> friend;
    static ArrayList<ArrayList<Integer>> enemy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 학생 수
        int m = Integer.parseInt(br.readLine()); // 인간관계

        friend = new ArrayList<>();
        enemy = new ArrayList<>();
        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            friend.add(new ArrayList<>());
            enemy.add(new ArrayList<>());
            parent[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0); // F 친구, E 원수
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (c == 'E') {
                enemy.get(p).add(q);
                enemy.get(q).add(p);
            } else if (c == 'F') {
                friend.get(p).add(q);
                friend.get(q).add(p);
            }
        }

        // 원수의 원수는 친구
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < enemy.get(i).size(); j++) {
                int e = enemy.get(i).get(j);

                for (int k = 0; k < enemy.get(e).size(); k++) {
                    if (i == enemy.get(e).get(k)) continue;

                    friend.get(i).add(enemy.get(e).get(k));
                    friend.get(enemy.get(e).get(k)).add(i);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < friend.get(i).size(); j++) {
                union(i, friend.get(i).get(j));
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(parent[i]);
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