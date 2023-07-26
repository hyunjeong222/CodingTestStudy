package ka.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제 이름(난이도) : 스타트링크(실버1)
 * 시간 : 216 ms
 * 메모리 : 58800 KB
 * 링크 : https://www.acmicpc.net/problem/5014
 */
public class BOJ_5014 {
    static int F, S, G;
    static int[] dy = new int[2];
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        F = Integer.parseInt(s[0]);
        S = Integer.parseInt(s[1]);
        G = Integer.parseInt(s[2]);
        dy[0] = Integer.parseInt(s[3]);
        dy[1] = -Integer.parseInt(s[4]);

        map = new int[F + 1];
        bfs();
    }

    /*public static void bfs(int floor) {
        if (G < S && dy[1] == 0) {
            System.out.println("use the stairs");
        }
        if (floor == G) {
            return;
        } else if(G > floor) {
            cnt++;
            bfs(floor + dy[0]);
        } else {
            cnt++;
            bfs(floor - dy[1]);
        }

    }*/

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[F + 1];
        q.add(S);
        visited[S] = true;
        map[S] = 0;

        while (!q.isEmpty()) {
            int pos = q.poll();
            if (pos == G) {
                System.out.println(map[pos]);
                return;
            }

            for (int i = 0; i < dy.length; i++) {
                int ny = pos + dy[i];
                // 1층 미만이거나 최대층보다 높아지면 건너뛰기
                if(ny > F || ny < 1) {
                    continue;
                }
                if (!visited[ny]) {
                    visited[ny] = true;
                    q.add(ny);
                    map[ny] = map[pos] + 1;
                }
            }
        }
        System.out.println("use the stairs");
    }
}
