import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, b;
    static int[][] map;
    static int min = 257, max = 0;
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
        // System.out.println(min);
        // System.out.println(max);

        for (int i = max; i >= min; i--) {
            blockCheck(i);
        }

        sb.append(minTime).append(" ").append(height).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static void blockCheck(int h) {
        int nowTime = 0;
        int blockCnt = b;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 최소 시간보다 커지면 종료
                if (nowTime > minTime) return;

                if (map[i][j] > h) { // 1번 작업 : 땅 파기
                    int sub = map[i][j]-h;
                    nowTime += 2*sub;
                    blockCnt += sub;
                } else if (map[i][j] < h) { // 2번 작업 : 땅 놓기
                    int sub = h-map[i][j];
                    nowTime += sub;
                    blockCnt -= sub;
                }
            }
        }

        if (blockCnt >= 0 && minTime > nowTime) {
            minTime = nowTime;
            height = h;
        }
    }
}