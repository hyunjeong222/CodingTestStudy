package src.jung.week5;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 용액(GOL5)
 * 시간 : 268ms
 * 메모리 : 31408KB
 * 링크 : https://www.acmicpc.net/problem/2467
 * */
public class Boj_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 전체 용액의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n]; // 용액의 특성값
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 포인터 사용해서 비교
        int start = 0; // 시작
        int end = arr.length - 1; // 끝

        int max = Integer.MAX_VALUE; // int 범위 : -2,147,483,648 ~ 2,147,483,647

        int ans1 = 0; // 용액 1
        int ans2 = 0; // 용액 2

        while (start < end) {
            int sum = arr[start] + arr[end]; // 용액 특성값의 합

            if (Math.abs(sum) < max) { // 0과 가까운 특성값을 찾아내기 위해 절댓값 사용
                ans1 = arr[start];
                ans2 = arr[end];
                max = Math.abs(sum);
            } else if (sum > 0) { // 더 작아져야 하므로 오른쪽 포인터 이동
                end--;
            } else { // 더 커져야 하므로 왼쪽 포인터 이동
                start++;
            }
        }
        bw.append(ans1 + " " + ans2 + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
