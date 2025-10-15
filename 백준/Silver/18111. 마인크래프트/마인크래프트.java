import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, b;
    static int[][] map;
    static int min = 257, max = 0;
    // 땅을 고르는 최소 시간, 땅의 높이
    static int minTime = Integer.MAX_VALUE, height = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken()); // 인벤토리에 들어있는 블록 개수

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        // 블록을 하나 꺼내 쌓는게
        // 시간이 덜 걸림 (1초)
        for (int h = max; h >= min; h--) {
            // 땅 고르기
            blockCheck(h);
        }

        sb.append(minTime).append(" ").append(height).append("\n");
        System.out.println(sb.toString());

        br.close();
    }

    private static void blockCheck(int h) {
        int t = 0, block = b;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (t > minTime) return;

                if (map[i][j] > h) { // 작업 1 : 블록 제거 2초
                    int sub = map[i][j]-h; // 제거한 블록
                    t += sub*2;
                    block += sub; // 제거한 블록 인벤토리 저장
                } else if (map[i][j] < h) { // 작업 2 : 블록 쌓기 1초
                    int sub = h-map[i][j]; // 쌓아야 하는 블록
                    t += sub;
                    block -= sub; // 인벤토리에서 블록 꺼내서 쌓기
                }
            }
        }

        if (block >= 0 && minTime > t) {
            minTime = t;
            height = h;
        }
    }
}