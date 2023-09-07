package src.jung.week3;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 통나무 건너뛰기(SIL1)
 * 시간 : 540ms, 424ms
 * 메모리 : 46644KB, 48496KB
 * 링크 : https://www.acmicpc.net/problem/11497
 * */
public class Boj_11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine()); //  통나무의 개수
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            // 가장 큰 수가 가운데, 그리고 왼쪽 오른쪽 나눠 채우기
            int[] tmp = new int[n];

            int left = 0;
            int right = n-1;

            // 왼쪽 오른쪽에 하나씩 정렬된 통나무 놓아 가장 작은 차이 만들기
            // 1 3 5 4 2
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    tmp[right--] = arr[i];
                } else {
                    tmp[left++] = arr[i];
                }
            }
            // 처음과 끝 통나무 크기 비교
            // 처음과 끝 통나무도 인접
            int max = Math.abs(tmp[0]-tmp[n-1]);
            // 인접한 통나무와 크기비교 해 max 값 바꿔주기
            for (int j = 1; j < n; j++) {
                max = Math.max(max, Math.abs(tmp[j]-tmp[j-1]));
            }
            bw.append(max + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
