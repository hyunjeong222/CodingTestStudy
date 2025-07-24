import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static ArrayList<ArrayList<Integer>> list;
    static Set<String> checked;
    static int[] apples;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        k = Integer.parseInt(st.nextToken()); // 최대 방문 노드 개수

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        apples = new int[n]; // 사과 여부
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apples[i] = Integer.parseInt(st.nextToken());
        }

        checked = new HashSet<>();
        int startNode = (1 << 0); // 루트 노드
        int startApple = apples[0];

        dfs(0, startNode, 1, startApple);

        System.out.println(max);

        br.close();
    }

    private static void dfs(int now, int node, int nodeCnt, int apple) {
        max = Math.max(max, apple);

        // 최대 k개의 노드를 방문
        if (nodeCnt == k) return;

        for (int next : list.get(now)) {
            int nextNode = node | (1 << next); // next 노드를 방문했다고 표시
            boolean isNewNode = (node & (1 << next)) == 0; // 아직 방문 안 한 노드인지 확인

            // 같은 노드에 동일한 방문 상태로 들어오는 경우 방지
            String key = next + " " + nextNode;
            if (checked.contains(key)) continue;

            int nextCnt = nodeCnt;
            int nextApple = apple;
            if (isNewNode) { // 아직 방문하지 않은 노드
                nextCnt++;
                nextApple += apples[next];
            }

            checked.add(key); // 현재 경로 넣기

            dfs(next, nextNode, nextCnt, nextApple);
        }
    }
}