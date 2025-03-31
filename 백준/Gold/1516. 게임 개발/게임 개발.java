import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] indegree, time, result;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        indegree = new int[n+1];
        time = new int[n+1];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int node = Integer.parseInt(st.nextToken()); // i 건물을 짓기 위해 먼저 지어야하는 건물의 번호
                if (node == -1) break;
                list.get(node).add(i);
                indegree[i]++;
            }
        }

        topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]+time[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void topologicalSort() {
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) que.offer(i);
        }

        // 특정 건물을 짓기 전까지 걸린 시간
        result = new int[n+1];

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                indegree[next]--;

                result[next] = Math.max(result[next], result[now]+time[now]);

                if (indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }
    }
}