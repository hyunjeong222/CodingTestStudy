package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 문자열 집합(SIL3)
 * 시간 : 336ms
 * 메모리 : 39092KB
 * 링크 : https://www.acmicpc.net/problem/14425
 * */
public class Boj_14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집합 S에 포함되어 있는 문자열들
        int m = Integer.parseInt(st.nextToken()); //검사해야 하는 문자열들

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }
        // System.out.println(set);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (set.contains(br.readLine())) ans++;
        }

        /*
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        // System.out.println(Arrays.toString(arr));

        int ans = 0;
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(str)) ans++;
            }
        }
        */

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
