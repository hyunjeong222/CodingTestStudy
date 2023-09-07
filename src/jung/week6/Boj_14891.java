package src.jung.week6;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 톱니바퀴(GOL5)
 * 시간 : 84ms
 * 메모리 : 11880KB
 * 링크 : https://www.acmicpc.net/problem/14891
 * */
public class Boj_14891 {
    static int[][] wheels;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        wheels = new int[4][8]; // 8개의 톱니를 가지고 있는 톱니바퀴 4개
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = s.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine()); // 회전 횟수

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
            int r = Integer.parseInt(st.nextToken()); // 회전 방향

            // 각 톱니바퀴의 회전 방향 구하기
            // 1: 시계 방향, -1: 반시계 방향
            int[] directions = getDirections(wheels, n, r);
            // System.out.println(Arrays.toString(directions));

            // 회전
            for (int j = 0; j < 4; j++) {
                if (directions[j] != 0) rotation(wheels[j], directions[j]);
            }
            // System.out.println(Arrays.deepToString(wheels));
        }
        // 점수 구하기
        int ans = getScore(wheels);

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] getDirections(int[][] wheels, int n, int r) {
        int[] directions = new int[4];
        directions[n] = r;

        // 현재 톱니바퀴 번호 기준 왼쪽
        // 9시 방향 idx: 6
        // 6시 방향 idx: 2
        for (int i = n; i > 0; i--) {
            if (wheels[i][6] == wheels[i-1][2]) break; // 맞닿은 극이 같다면 회전 X
            directions[i-1] = directions[i] * -1;
        }

        // 현재 톱니바퀴 번호 기준 오른쪽
        for (int i = n; i < 3; i++) {
            if (wheels[i][2] == wheels[i+1][6]) break;
            directions[i+1] = directions[i] * -1;
        }

        return directions;
    }

    private static void rotation(int[] wheel, int direction) {
        if (direction == 1) { // 시계 방향
            int tmp = wheel[7]; // 원형, 처음과 끝이 이어짐
            for (int i = 7; i > 0; i--) {
                wheel[i] = wheel[i-1];
            }
            wheel[0] = tmp;
        } else if (direction == -1) { // 반시계 방향
            int tmp = wheel[0];
            for (int i = 0; i < 7; i++) {
                wheel[i] = wheel[i+1];
            }
            wheel[7] = tmp;
        }
    }

    private static int getScore(int[][] wheels) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (wheels[i][0] == 1) { // 12시방향 s극
                score += Math.pow(2, i);
            }
        }
        return score;
    }
}