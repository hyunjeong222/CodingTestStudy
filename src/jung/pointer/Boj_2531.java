package src.jung.pointer;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회전 초밥(SIL1)
 * 시간 : 144ms
 * 메모리: 16424KB
 * 링크 : https://www.acmicpc.net/problem/2531
 */
public class Boj_2531 {
    // 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값
    static int n, d, k, c;
    static int[] arr, eaten;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        arr = new int[n]; // 초밥의 종류
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.append(slide() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int slide() {
        eaten = new int[d+1]; // 먹은 초밥 체크
        int cnt = 0; // 먹은 초밥 가짓수
        for (int i = 0; i < k; i++) { // 연속으로 먹을 수 있는 만큼
            if (eaten[arr[i]] == 0) cnt++; // 먹지 않았던 초밥 종류라면
            eaten[arr[i]]++;
        }

        int max = cnt; // 최대로 먹은 초밥 가짓수
        // 회전 초밥 벨트 돌리면서 초밥먹기
        for (int i = 1; i < n; i++) {
            if (max <= cnt) { // 최대값 갱신
                if (eaten[c] == 0) max = cnt+1; // 쿠폰 사용 안했다면 사용
                else max = cnt;
            }
            // 앞 초밥 못먹음
            eaten[arr[i-1]]--;
            if (eaten[arr[i-1]] == 0) cnt--;
            // 뒤 초밥 먹을 수 있음
            if (eaten[arr[(i+k-1)%n]] == 0) cnt++;
            eaten[arr[(i+k-1)%n]]++;

            if (max <= cnt) { // 최대값 갱신
                if (eaten[c] == 0) max = cnt+1; // 쿠폰 사용 안했다면 사용
                else max = cnt;
            }
        }
        return max;
    }
}
