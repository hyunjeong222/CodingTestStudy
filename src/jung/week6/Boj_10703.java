package src.jung.week6;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 유성(SIL1)
 * 시간 : 1128ms
 * 메모리 : 61976KB
 * 링크 : https://www.acmicpc.net/problem/10703
 * */
public class Boj_10703 {
    static int r, s;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); // 사진의 세로
        s = Integer.parseInt(st.nextToken()); // 사진의 가로

        // 복원 전 사진 입력
        map = new char[r][s];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < s; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // System.out.println(Arrays.deepToString(map));

        // x 유성, # 땅, . 공기
        // 유성이 떨어져도 땅과 유성은 원형을 유지 -> 떨어질 수 있는 높이는 정해져있음
        // 땅과 유성의 거리차가 가장 작은 거리 선택
        int min = getDistance();
        // System.out.println(min);
        // 이동
        getMove(min);
        // System.out.println(Arrays.deepToString(map));
        // 출력
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < s; j++) {
                bw.append(map[i][j]);
            }
            bw.append("\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    private static int getDistance() {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < s; j++) {
            int cnt = 0; // 각 열의 땅과 유성의 거리
            for (int i = 0; i < r; i++) {
                if (i+1 < s && map[i][j] == 'X' && map[i+1][j] == '.') {
                    for (int k = i+1; k < r-1; k++) {
                        if (map[k][j] == '#') break;
                        if (map[k][j] == 'X') {
                            cnt = 0;
                            break;
                        }
                        if (map[k][j] == '.') cnt++;
                    }
                }
                if (cnt > 0 && map[i][j] == '#'){
                    min = Math.min(min, cnt);
                    cnt = 0;
                }
            }
        }
        return min;
    }

    private static void getMove(int min) {
        for (int j = 0; j < s; j++) {
            for (int i = r-1; i >= 0; i--) {
                if (map[i][j] == 'X' && map[i+min][j] == '.') {
                    char tmp = map[i][j];
                    map[i][j] = map[i+min][j];
                    map[i+min][j] = tmp;
                }
            }
        }
    }
}
