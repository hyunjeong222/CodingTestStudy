import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dj = {-1, 0, 1};
    static public class State {
        int pos; int jump; int cnt;
        public State(int pos, int jump, int cnt) {
            this.pos = pos; this.jump = jump; this.cnt = cnt;
        }
    }
    static boolean[] smallRock;
    static HashSet<Integer>[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        smallRock = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            smallRock[Integer.parseInt(br.readLine())] = true;
        }

        visited = new HashSet[n+1];
        for (int i = 0; i <= n; i++) {
            visited[i] = new HashSet<>();
        }

        System.out.println(bfs());

        br.close();
    }

    private static int bfs() {
        Queue<State> que = new LinkedList<>();
        que.offer(new State(1, 0, 0));

        while (!que.isEmpty()) {
            State now = que.poll();

            for (int i = 0; i < 3; i++) {
                int nj = now.jump+dj[i];

                if (nj <= 0) continue;

                int np = now.pos+nj;

                if (np == n) {
                    return now.cnt+1;
                }

                if (np > n || smallRock[np]) continue;

                if (visited[np].contains(nj)) continue;

                visited[np].add(nj);
                que.offer(new State(np, nj, now.cnt+1));
            }
        }

        return -1;
    }
}