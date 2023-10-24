import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Node>[] list;
    static boolean[] checked;
    static int ans;
    static public class Node {
        int num; int value; // 노드의 번호, 가중치
        public Node (int num, int value) {
            this.num = num; this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine()); // 노드의 개수
        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 부모의 노드
            int b = Integer.parseInt(st.nextToken()); // 자식읜 노드
            int v = Integer.parseInt(st.nextToken()); // 간선의 가중치

            // 양방향
            list[a].add(new Node(b, v));
            list[b].add(new Node(a, v));
        }

        ans = 0; // 트리의 지름
        for (int i = 1; i <= n; i++) {
            checked = new boolean[n+1];
            checked[i] = true;
            dfs(i, 0);
        }

        System.out.println(ans);
    }

    private static void dfs(int num, int dist) {
        if (ans < dist) ans = dist; // 최대 지름 갱신

        for (Node node : list[num]) {
            if (!checked[node.num]) {
                checked[node.num] = true;
                dfs(node.num, node.value + dist);
            }
        }
    }
}