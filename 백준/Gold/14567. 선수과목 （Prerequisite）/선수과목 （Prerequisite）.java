import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] indegree;
    static int[] ans;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 과목의 수
        m = Integer.parseInt(st.nextToken()); // 선수 조건의 수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        indegree = new int[n+1];

        int a, b; // A번 과목이 B번 과목의 선수과목
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            indegree[b]++;
        }

        topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void topologicalSort() {
        Queue<Integer> que = new LinkedList<>();
        ans = new int[n+1];

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                que.offer(i);
                ans[i] = 1;
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    que.offer(next);
                    ans[next] = ans[now]+1;
                }
            }
        }
    }
}