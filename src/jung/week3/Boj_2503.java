package src.jung.week3;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 숫자 야구(SIL3)
 * 시간 : 168ms,100ms
 * 메모리: 18032KB, 14308KB
 * 링크 : https://www.acmicpc.net/problem/2503
 * */
public class Boj_2503 {
    static boolean[] check = new boolean[988]; // 가능한 숫자 체크
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 질문횟수

        // 가능한 정답인 경우 check 배열을 true, 아니면 false
        for (int i = 123; i <= 987; i++) {
            String num  = Integer.toString(i);

            // 0이 들어가면 안되고, 숫자가 중복되면 안됨
            if (num.charAt(0) == '0' || num.charAt(1) == '0' || num.charAt(2) == '0') continue;
            if (num.charAt(0) == num.charAt(1) || num.charAt(0) == num.charAt(2) ||num.charAt(1) == num.charAt(2)) continue;

            check[i] = true;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int que = Integer.parseInt(st.nextToken()); // 민혁이가 질문한 세 자리 수
            int strike = Integer.parseInt(st.nextToken()); // 스트라이크 개수
            int ball = Integer.parseInt(st.nextToken()); // 볼 개수

            // 가능한 모든 정답 탐색
            for (int j = 123; j <= 987; j++) {
                // 정답 가능성이 있는 수라면
                if (check[j]) {
                    int s_num = 0;
                    int b_num = 0;

                    // 정답 가능성 있는 수와 입력 받은 수의 스트라이크, 볼 비교
                    for (int k = 0; k < 3; k++) {
                        char qud_split = Integer.toString(que).charAt(k);
                        for (int l = 0; l < 3; l++) {
                            char ans_split = Integer.toString(j).charAt(l);

                            // 자리수와 값이 같으면 스트라이크
                            // 자리수 다른데 값은 같으면 볼
                            if (qud_split == ans_split && k == l) s_num++;
                            else if (qud_split == ans_split && k != l) b_num++;
                        }
                    }
                    // 입력받은 스트라이크, 볼 값과 계산한 값이 같으면 정답 가능성 있는 수
                    if (strike == s_num && ball == b_num) check[j] = true;
                    else check[j] = false;
                }
            }
        }
        int ans = 0;
        for (int i = 123; i <= 987; i++) {
            if (check[i]) ans++;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
