package src.jung.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 스타트링크(SIL1)
 * 시간 : 176ms
 * 메모리 : 55116KB
 * 링크 :https://www.acmicpc.net/problem/5014
 * */
public class Boj_5014 {
    static int f, s, g, u, d;
    static int[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken()); // 총 층 수
        s = Integer.parseInt(st.nextToken()); // 현재 위치
        g = Integer.parseInt(st.nextToken()); // 도착 위치
        u = Integer.parseInt(st.nextToken()); // 위로
        d = Integer.parseInt(st.nextToken()); // 아래로

        checked = new int[f+1];

        int min = bfs(s); // G층에 가기위해 버튼 누르는 최소 횟수
        if (min == -1) bw.append("use the stairs" + "\n");
        else bw.append(min + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    private static int bfs(int s) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(s);
        checked[s] = 1;

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (cur == g) {
                return checked[cur]-1;
            }
            if (cur+u <= f && checked[cur+u] == 0) {
                checked[cur+u] = checked[cur]+1;
                que.offer(cur+u);
            }
            if (cur-d > 0 && checked[cur-d] == 0) {
                checked[cur-d] = checked[cur]+1;
                que.offer(cur-d);
            }
        }
        if (checked[g] == 0) return -1;
        return -1;
    }
}
