import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static public class pos {
        int n; // 말의 번호
        int d; // 말의 방향
        public pos(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
    static int n , k;
    static LinkedList<pos>[][] state; // 말의 번호, 방향을 밑에서부터 저장
    static int[][] horse; // 각 말의 행, 열 저장
    static int[][] color; // 각 위치의 색 저장
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int ans; // 턴 횟수
    static int x, y, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 체스판의 크기
        k = Integer.parseInt(st.nextToken()); // 말의 개수

        color = new int[n][n]; // 체스판의 정보, 0 흰색, 1 빨간색, 2 파란색
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horse = new int[k][2]; // 말의 x, y 위치
        state = new LinkedList[n][n]; // 쌓인 말의 순서
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // 행, 열의 번호
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;
            // 이동 방향
            // 1 오른쪽, 2 왼쪽, 3 위, 4 아래
            d = Integer.parseInt(st.nextToken());

            if (d == 1) d = 0;
            else if (d == 4) d = 1;

            horse[i][0] = x;
            horse[i][1] = y;

            state[x][y].add(new pos(i, d));
        }
        
        ans = game(); // 게임 시작
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int game() {
        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j < k; j++) {
                int x = horse[j][0];
                int y = horse[j][1];
                int d = state[x][y].get(0).d;

                if (state[x][y].get(0).n != j) continue;

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || color[nx][ny] == 2) {
                    d = (d + 2) % 4;
                    state[x][y].get(0).d = d;
                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || color[nx][ny] == 2) continue;
                    else {
                        if (move(x, y, nx, ny, color[nx][ny])) {
                            return i;
                        }
                    }
                } else {
                    if (move(x, y, nx, ny, color[nx][ny])) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean move(int x, int y, int nx, int ny, int color) {
        if (color == 0) { // 흰색
            while (state[x][y].size() > 0) {
                pos tmp = state[x][y].removeFirst(); // list 앞부터 빼서 옮겨 담기
                horse[tmp.n][0] = nx;
                horse[tmp.n][1] = ny;
                state[nx][ny].add(tmp);
            }
        } else { // 빨간색
            while (state[x][y].size() > 0) {
                pos tmp = state[x][y].removeLast(); // list 뒤부터 빼서 옮겨 담기
                horse[tmp.n][0] = nx;
                horse[tmp.n][1] = ny;
                state[nx][ny].add(tmp);
            }
        }
        
        if (state[nx][ny].size() >= 4) return true;
        else return false;
    }
}