import java.io.*;

public class Main {
    static int n;
    static char[][] map;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Character.isDigit(map[i][j])) {
                    check(i, j, map[i][j]-'0');
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '*' || map[i][j] == '#') cnt++;
            }
        }

        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void check(int x, int y, int cur) {
        for (int i = 0; i < 8; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (map[nx][ny] == '#' && cur != 0) {
                    cur--;
                    map[nx][ny] = '*';
                } else if (map[nx][ny] == '*' && cur != 0) {
                    cur--;
                } else if (map[nx][ny] == '#' && cur == 0) {
                    map[nx][ny] = '-';
                }
            }
        }
    }
}