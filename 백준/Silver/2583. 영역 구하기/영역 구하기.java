import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int m, n, k;
    static int startX, startY, endX, endY;
    static int[][] map;
    static int count; // 영역의 넓이
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int nx, ny;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 직사각형의 개수

        // m X n
        map = new int[m][n];

        while (k --> 0) {
            st = new StringTokenizer(br.readLine());

            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            // 직사각형 부분 1로 변경해주기
            for (int i = startY; i < endY; i++) {
                for (int j = startX; j < endX; j++) {
                    map[i][j] = 1;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>(); // 분리된 영역의 넓이를 담을 list
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) { // 분리된 영역이라면
                    count = 0; // 영역의 넓이 리셋
                    dfs(i, j);
                    list.add(count);
                }
            }
        }
        
        Collections.sort(list); // 오름차순 정렬
        bw.append(list.size() + "\n");
        for (int i : list) {
            bw.append(i + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y) {
        map[x][y] = 1;
        count++; // 넓이 증가

        for (int i = 0; i < 4; i++) {
            nx = dx[i] + x;
            ny = dy[i] + y;

            if (rangeCheck() && map[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
    }

    private static boolean rangeCheck() {
        return (nx >= 0 && ny >= 0 && nx < m && ny < n);
    }
}