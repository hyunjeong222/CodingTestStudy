import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] checked;
    static int size = 100001;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        checked = new int[size];

        if (n == k) bw.append(0 + "\n");
        else {
            ans = bfs(n);
            bw.append(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x) {
        Queue<Integer> que  = new LinkedList<>();
        que.offer(x);
        checked[x] = 1;

        while (!que.isEmpty()) {
            int location = que.poll();
            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = location - 1;
                else if (i == 1) next = location + 1;
                else next = location * 2;

                if (next == k) return checked[location];

                if (next >= 0 && next < checked.length && checked[next] == 0) {
                    que.offer(next);
                    checked[next] = checked[location]+1;
                }
            }
        }
        return -1;
    }
}