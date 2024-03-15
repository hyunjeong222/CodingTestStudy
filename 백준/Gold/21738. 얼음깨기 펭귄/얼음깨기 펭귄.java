import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, s, p;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 얼음 블록의 개수
        s = Integer.parseInt(st.nextToken()); // 지지대 얼음의 개수
        p = Integer.parseInt(st.nextToken()); // 펭귄이 위치한 얼음 번호
        list = new ArrayList<>();
        for (int i = 1; i <= n+1; i++) {
            list.add(new ArrayList<>());
        }
        int a, b;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        bfs(p);
        Arrays.sort(dist, 1, s+1);
        System.out.println(n-1-(dist[1]+dist[2]));
    }

    private static void bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] checked = new boolean[n+1];
        dist = new int[n+1];
        que.offer(x);
        checked[x] = true;
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int next : list.get(now)) {
                if (!checked[next]) {
                    que.offer(next);
                    checked[next] = true;
                    dist[next] = dist[now]+1;
                }
            }
        }
    }
}