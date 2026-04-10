import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, m, p;
    static int[] D, order, count; // 진입 차수 배열
    static List<Integer>[] graph; // 인접 리스트
    static Queue<Integer> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken()); // 테스트 케이스 번호
            m = Integer.parseInt(st.nextToken()); // 노드의 수
            p = Integer.parseInt(st.nextToken()); // 간선의 수

            D = new int[m+1];
            order = new int[m+1]; // 최대 순서 값
            count = new int[m+1]; // 그 최대값이 몇 개 들어왔는지
            graph = new LinkedList[m+1];
            for (int i = 0; i <= m; i++) {
                graph[i] = new LinkedList<>();
            }

            int a, b;
            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                D[b]++;
            }

            topologySort();

            sb.append(k).append(" ").append(order[m]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void topologySort() {
        que = new LinkedList<>();
        // 진입 차수가 0인 노드 큐에 추가
        for (int i = 1; i <= m; i++) {
            if (D[i] == 0) {
                order[i] = 1;
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : graph[now]) {
                if (order[next] < order[now]) { // 더 큰 순서를 발견
                    order[next] = order[now];
                    count[next] = 1;
                } else if (order[next] == order[now]) {
                    count[next]++;
                }

                if (--D[next] == 0) { // 모든 부모 처리 완료
                    // 최대값이 2개 이상이면 +1
                    order[next] += count[next] >= 2 ? 1 : 0;
                    que.offer(next);
                }
            }
        }
    }
}