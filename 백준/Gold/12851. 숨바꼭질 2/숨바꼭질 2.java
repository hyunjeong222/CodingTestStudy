import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] checked;
    static int size = 100001;
    static int ans = Integer.MAX_VALUE;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치

        checked = new int[size];

        // 수빈이가 더 앞에 있는 경우
        // 뒤로 가는 경우만 존재
        if (n >= k) bw.append(n-k + "\n" + 1);
        else {
            bfs(n);
            bw.append(ans + "\n" + cnt);
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        checked[x] = 1;

        while (!que.isEmpty()) {
            x = que.poll();

            // 최소보다 시간이 더 오래걸리면 비교할 필요 없음
            if (ans < checked[x]) continue;

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = x - 1;
                else if (i == 1) next = x + 1;
                else next = x * 2;

                if (next == k) {
                    ans = checked[x];
                    cnt++;
                }

                if (next < 0 || next >= checked.length) continue;

                if (checked[next] == 0 || checked[next] == checked[x]+1) {
                    que.offer(next);
                    checked[next] = checked[x]+1;
                }
            }
        }
        return -1;
    }
}