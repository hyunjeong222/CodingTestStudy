import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, a, b;
    static String[] str;
    static List<Integer>[] list;
    static boolean[] checked;
    static int[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        k = Integer.parseInt(st.nextToken()); // 파일의 총 개수

        list = new List[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        str = new String[n+1];
        for (int i = 1; i <= n; i++) {
            str[i] = br.readLine();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                int cnt = 0;
                for (int p = 0; p < k; p++) {
                    if (str[i].charAt(p) != str[j].charAt(p)) cnt++;
                    if (cnt > 1) break;
                }
                
                if (cnt == 1) {
                    list[i].add(j);
                    list[j].add(i);
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        prev = new int[n+1];
        Arrays.fill(prev, -1);
        bfs();

        List<Integer> path = new ArrayList<>();
        int now = b;
        while (now != -1) {
            path.add(now);
            now = prev[now];
        }
        Collections.reverse(path);

        for (int x : path) {
            sb.append(x).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.add(a); // 시작점
        checked = new boolean[n+1];
        checked[a] = true;

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list[now]) {
                if (!checked[next]) {
                    checked[next] = true;
                    prev[next] = now;
                    if (next == b) return; // 도착점
                    que.offer(next);
                }
            }
        }

        System.out.println(-1);
        System.exit(0);
    }
}