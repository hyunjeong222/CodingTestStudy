package src.jung.week10;

import java.io.*;

/**
 * 문제 이름(난이도) : 사다리 타기(GOL)
 * 시간 : 96ms
 * 메모리 : 12964KB
 * 링크 : https://www.acmicpc.net/problem/2469
 */
public class Boj_2469 {
    static int k, n, lineIndex;
    static char[] results, origins;
    static char[][] ladder;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine()); // 참가한 사람의 수
        n = Integer.parseInt(br.readLine()); // 가로 줄의 수

        origins = new char[k]; // 참가자들의 원래 순서
        for (int i = 0; i < k; i++) {
            origins[i] = (char)('A' + i);
        }
        // System.out.println(Arrays.toString(origins));

        results = new char[k]; // 참가자들의 최종 순서
        String p = br.readLine();
        for (int i = 0; i < p.length(); i++) {
            results[i] = p.charAt(i);
        }
        // System.out.println(Arrays.toString(participants));

        // 가로 막대가 없는 경우 *, 있는 경우 -, 감추어진 가로 줄은 ?
        ladder = new char[n][k-1]; // 사다리
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < k-1; j++) {
                ladder[i][j] = s.charAt(j);
                if (s.charAt(j) == '?') {
                    lineIndex = i; // ? 채워진 행의 인덱스 저장
                    continue;
                }
            }
        }
        // System.out.println(Arrays.deepToString(ladder));
        // System.out.println(lineIndex);

        // 1. 참가자들의 원래 순서로 ? 행까지 사다리 진행
        for (int i = 0; i < lineIndex; i++) {
            for (int j = 0; j < k-1; j++) {
                // 가로 막대를 만났으면
                if (ladder[i][j] == '-') {
                    // 참가
                    char tmp = origins[j];
                    origins[j] = origins[j+1];
                    origins[j+1] = tmp;
                }
            }
        }

        // 2. 참가자들의 결과 순서로 ? 행까지 사다리 진행
        for (int i = n-1; i > lineIndex; i--) {
            for (int j = 0; j < k-1; j++) {
                // 가로 막대를 만났으면
                if (ladder[i][j] == '-') {
                    // 사다리 타기 실행해 위치 변경
                    char tmp = results[j];
                    results[j] = results[j+1];
                    results[j+1] = tmp;
                }
            }
        }

        // 두 결과 비교하며 문자열 완성
        // 1과 2의 알파벳들은 같거나 위치가 1만 차이나야 가능한 사다리
        // 위치가 2 이상 차이나면 불가능한 사다리
        sb = new StringBuilder();
        for (int i = 0; i < k-1; i++) {
            // 같다면 그냥 내려가기 *
            if (origins[i] == results[i]) sb.append('*');
            else if (origins[i] == results[i+1]) { // 1 차이라면 가로 막대
                sb.append('-');
                // 다음 반복에서 가로 막대 놓기 방지
                char tmp = origins[i];
                origins[i] = origins[i+1];
                origins[i+1] = tmp;
            } else { // 2 이상 차이, 불가능 사다리
                sb = new StringBuilder();
                for (int j = 0; j < k-1; j++) sb.append('x');
                break;
            }
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
