import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k, m;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 역의 수
        k = Integer.parseInt(st.nextToken()); // 하이퍼튜브가 서로 연결하는 역의 개수
        m = Integer.parseInt(st.nextToken()); // 하이퍼튜브 수

        // 1~n 역, n+1~m 까지는 하이퍼튜브 (가짜 역)
        list = new ArrayList<>();
        for (int i = 0; i <= n+m; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int hyperTube = n+i;
            for (int j = 0; j < k; j++) {
                int node = Integer.parseInt(st.nextToken());
                //
                list.get(node).add(hyperTube);
                list.get(hyperTube).add(node);
            }
        }

        int ans = bfs(1);
        System.out.println(ans);
    }

    private static int bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        int[] checked = new int[n+m+1]; // 거쳐간 횟수 기록

        // 시작점
        que.offer(start);
        checked[start] = 1;

        while (!que.isEmpty()) {
            int node = que.poll();

            if (node == n) { // 도착지
                return checked[node];
            }

            for (int connect : list.get(node)) {
                if (checked[connect] == 0) { // 아직 거쳐간 역 X
                    if (connect <= n) { // 역
                        checked[connect] = checked[node] + 1;
                        que.offer(connect);
                    } else { // 하이퍼튜브
                        checked[connect] = checked[node];
                        que.offer(connect);
                    }
                }
            }
        }

        return -1;
    }
}