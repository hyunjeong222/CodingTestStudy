package src.jung.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 회전 초밥(GOL4)
 * 시간 : 528ms
 * 메모리 : 168732KB
 * 링크 : https://www.acmicpc.net/problem/15961
 * */
public class Boj_15961 {
    static int n, d, k, c, sushi[], eaten[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 단, 2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d
        n = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[n]; // 초밥의 종류
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(slide());
        br.close();
    }

    static int slide() {
        eaten = new int[d+1]; // 먹은 초밥의 개수 저장 배열
        int cnt = 0; // k 안에서 먹은 중복없는 스시 개수

        // k 만큼 연속해서 스시 먹기
        for (int i = 0; i < k; i++) {
            if (eaten[sushi[i]] == 0) { // 먹은적 없다면
                cnt++; // 먹은 스시 개수 증가
            }
            eaten[sushi[i]]++;
        }

        int max = cnt; // 쿠폰까지 사용해서 먹은 개수

        // 회전시키면서
        for (int i = 1; i < n; i++) {
            // 슬라이드 이동
            // 앞쪽 스시 못먹음
            // 먹은적 없다면 먹은 스시 -1
            eaten[sushi[i-1]]--;
            if (eaten[sushi[i-1]] == 0) cnt--;
            // 슬라이드 이동
            // 뒤쪽 스시 먹음
            // 먹은적 없다면 새로운 스시니까 +1
            if (eaten[sushi[(i+k-1)%n]] == 0) cnt++; // 회전하므로 % n
            eaten[sushi[(i+k-1)%n]]++;

            // 마지막 비교 ***
            if (max <= cnt) {
                if(eaten[c] == 0) {
                    max = cnt+1; // 쿠폰으로 하나 더 먹을 수 있음
                }else {
                    max = cnt;
                }
            }
        }
        return max;
    }
}