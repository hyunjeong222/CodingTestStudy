import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int[] indegree;
    static int n, k;
    static int[] time;
    static int[] result;
    static int w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 건물의 개수
            k = Integer.parseInt(st.nextToken()); // 건설 규칙의 수
            time = new int[n+1]; // 각 건물당 건설에 걸리는 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            indegree = new int[n+1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.get(x).add(y);
                indegree[y]++;
            }
            w = Integer.parseInt(br.readLine()); // 승리하기 위해 건설해야 할 건물의 번호

            getTime();
            sb.append(result[w]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void getTime() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        result = new int[n+1]; // 각 건물당 건설에 걸리는 시간
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) pq.offer(i);
            result[i] = time[i]; // 기본 소요 시간
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();

            for (int next : list.get(now)) {
                // 이전 건물이 다 올라가야 현재 건물을 지을 수 있음
                result[next] = Math.max(result[next], result[now] + time[next]);

                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
    }
}