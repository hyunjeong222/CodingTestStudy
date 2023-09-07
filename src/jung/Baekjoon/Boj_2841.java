package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 외계인의 기타 연주(SIL1)
 * 시간 : 772ms
 * 메모리: 122872KB
 * 링크 : https://www.acmicpc.net/problem/2841
 * */
public class Boj_2841 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 멜로디에 포함되어 있는 음의 수
        int p = Integer.parseInt(st.nextToken()); // 한 줄에 있는 프렛의 수
        int total = 0;
        Stack<Integer>[] stack = new Stack[7];
        for (int i = 0; i < 7; i++) {
            stack[i] = new Stack<Integer>(); // 1번 줄부터 6번 줄, [P개의 프렛]
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line_num = Integer.parseInt(st.nextToken());
            int plat_num = Integer.parseInt(st.nextToken());
            while (!stack[line_num].isEmpty() && stack[line_num].peek() > plat_num) {
                stack[line_num].pop();
                total++;
            }
            if (stack[line_num].isEmpty() || stack[line_num].peek() < plat_num) {
                stack[line_num].push(plat_num);
                total++;
            }
        }
        bw.append(total + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
