import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Node>> list;
    static boolean[] checked;
    static int node;
    static int max = 0;
    static public class Node {
        int end; int cost;
        public Node(int end, int cost) {
            this.end = end; this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 정점
            while (true) {
                int e = Integer.parseInt(st.nextToken()); // 연결된 정점
                if (e == -1) break;
                int c = Integer.parseInt(st.nextToken()); // 거리
                list.get(s).add(new Node(e, c));
            }
        }

        checked = new boolean[v+1];
        // 임의의 노드(1)에서 가장 먼 노드까지의 거리 구하기
        dfs(1, 0);

        checked = new boolean[v+1];
        dfs(node, 0);

        System.out.println(max);
    }

    private static void dfs(int i, int dis) {
        if (dis > max) {
            max = dis;
            node = i;
        }

        checked[i] = true;

        for (Node next : list.get(i)) {
            if (!checked[next.end]) {
                checked[next.end] = true;
                dfs(next.end, next.cost+dis);
            }
        }
    }
}