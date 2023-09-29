import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int u, v;
    static ArrayList<ArrayList<Integer>> list;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        parents = new int[n+1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        while (n --> 1) {
            st = new StringTokenizer(br.readLine());
            // 트리 상에서 연결된 두 정점
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            // 양방향 연결
            list.get(u).add(v);
            list.get(v).add(u);
        }

        bfs(1); // 시작 노드
        for (int i = 2; i < parents.length; i++) {
            bw.append(parents[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        parents[start] = 1;

        while (!que.isEmpty()) {
            int parent = que.poll();

            for (int adj : list.get(parent)) {
                if (parents[adj] == 0) {
                    parents[adj] = parent;
                    que.offer(adj);
                }
            }
        }
    }
}