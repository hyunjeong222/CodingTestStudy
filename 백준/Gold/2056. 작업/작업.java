import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static int[] indegree, time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        indegree = new int[n+1];
        time = new int[n+1]; // 작업에 걸리는 시간
        int cnt = 0; // 선행 관계에 있는 작업의 개수
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int tmp = Integer.parseInt(st.nextToken()); // 선행 관계의 작업 번호
                list.get(tmp).add(i);
                indegree[i]++;
            }
        }

        int ans = topologicalSort();
        System.out.println(ans);
    }

    private static int topologicalSort() {
        int ans = 0; // 모든 작업을 완료하기 위한 최소 시간

        Queue<Integer> que = new LinkedList<>();
        int[] result = new int[n+1]; // 각각의 작업을 수행하는데 걸리는 시간
        for (int i = 1; i <= n; i++) {
            result[i] = time[i];

            if (indegree[i] == 0) { // 진입차수가 0인 작업을 큐에 담기
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                // 인접한 노드의 진입차수 갱신
                indegree[next]--;
                // 이전에 모든 작업이 끝나지 않으면 다음 작업을 수행할 수 없기 때문에 최장거리를 구한다 생각
                result[next] = Math.max(result[next], result[now]+time[next]);
                // 갱신된 노드의 진입차수가 0이면 큐에 담기
                if (indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, result[i]);
        }
        return ans;
    }
}