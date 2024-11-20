import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; static int[] dy = {-1, 0, 1, 0}; // 좌하우상
    static int[] dc = {1, 1, 2, 2}; // 토네이도가 각 방향으로 이동하는 횟수, 한 사이클당 +2
    // 모래가 퍼지는 방향
    static int[][] dsx = {{-1, 1, -2, -1, 1, 2, -1, 1, 0},
            {-1, -1, 0, 0, 0, 0, 1, 1, 2},
            {1, -1, 2, 1, -1, -2, 1, -1, 0},
            {1, 1, 0, 0, 0, 0, -1, -1, -2}};
    static int[][] dsy = {{1, 1, 0, 0, 0, 0, -1, -1, -2},
            {-1, 1, -2, -1, 1, 2, -1, 1, 0},
            {-1, -1, 0, 0, 0, 0, 1, 1, 2},
            {1, -1, 2, 1, -1, -2, 1, -1, 0}};
    static int[] rate = {1, 1, 2, 7, 7, 2, 10, 10, 5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = simulation(n/2, n/2); // 시작점
        System.out.println(ans);
    }

    private static int simulation(int x, int y) {
        int totalOutSand = 0; // 격자의 밖으로 나간 모래의 양

        // 현재위치
        int currentX = x;
        int currentY = y;

        while (true) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < dc[d]; i++) {
                    // 현재 위치에서 다음 위치로 이동
                    int nx = currentX + dx[d];
                    int ny = currentY + dy[d];

                    // 격자의 밖으로 나갔다면
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        return totalOutSand; // 모래 리턴
                    }

                    // 모래 뿌리기
                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int spreadTotal = 0; // 알파로 이동하는 모래의 양은 비율이 적힌 칸으로 이동하지 않은 남은 모래의 양

                    for (int j = 0; j < 9; j++) {
                        int sx = nx + dsx[d][j];
                        int sy = ny + dsy[d][j];
                        int spreadSand = (sand*rate[j])/100; // 비율 칸으로 이동하는 비율만큼의 모래

                        if (sx < 0 || sy < 0 || sx >= n || sy >= n) { // 격자 밖으로 나가면
                            totalOutSand += spreadSand; // 격자의 밖으로 나간 모래의 양에 더해주기
                        } else {
                            // 모래의 해당 비율만큼 비율이 적혀있는 칸으로 이동
                            map[sx][sy] += spreadSand;
                        }
                        spreadTotal += spreadSand;
                    }

                    // α로 이동하는 모래의 양
                    int alphaX = nx+dx[d];
                    int alphaY = ny+dy[d];
                    int alphaSand = sand - spreadTotal; // 비율 칸으로 이동하지 않고 남은 모래
                    if (alphaX < 0 || alphaY < 0 || alphaX >= n || alphaY >= n) {
                        totalOutSand += alphaSand;
                    } else {
                        map[alphaX][alphaY] += alphaSand;
                    }

                    // 이동한 위치를 현재 위치로 업데이트
                    currentX = nx;
                    currentY = ny;
                }
            }

            // 한 사이클 돌았으면 이동 칸 수 +2
            for (int i = 0; i < 4; i++) {
                dc[i] += 2;
            }
        }
    }
}