package src.jung.week4;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 물통(GOL5)
 * 시간 : 144ms, 80ms
 * 메모리: 14688KB, 11792KB
 * 링크 : https://www.acmicpc.net/problem/2251
 * */
public class Boj_2251 {
    static boolean[][] checked;
    static int[] volume;
    static TreeSet<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        volume = new int[3];
        checked = new boolean[201][201];
        for (int i = 0; i < volume.length; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순으로 정렬
        ans = new TreeSet<>();

        dfs(0,0,volume[2]);

        for (int num : ans) {
            bw.append(num + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int a, int b, int c) {
        if (checked[a][b]) return;

        // 첫번째 물통이 비어있다면 세번째 물통의 물의 양 담기
        if (a == 0) ans.add(c);

        checked[a][b] = true;
        // a -> b, 물통 a의 물을 b로 옮기는 경우
        if (a+b > volume[1]) { // 물이 넘칠때
            dfs((a+b)-volume[1], volume[1], c);
        } else { // 그렇지 않을때
            dfs(0, a+b, c);
        }

        // b -> a
        if (a+b > volume[0]) {
            dfs(volume[0], (a+b)-volume[0], c);
        } else {
            dfs(a+b, 0, c);
        }

        // c -> a
        if (a+c > volume[0]) {
            dfs(volume[0], b, a+c-(volume[0]));
        } else {
            dfs(a+c, b, 0);
        }

        // c -> b
        if (b+c > volume[1]) {
            dfs(a, volume[1], b+c-(volume[1]));
        } else {
            dfs(a, b+c, 0);
        }

        // a + b = c, 물이 넘칠 가능성 X
        // a -> c
        dfs(a, 0, b+c);
        // b -> c
        dfs(0, b, a+c);
    }
}
