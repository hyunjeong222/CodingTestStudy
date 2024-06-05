import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static char[][] map;
    static int[] dx = {1, 1, 1};
    static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int ans;
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            map = new char[2][n];

            for (int i = 0; i < 2; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    if (Character.isDigit(map[i][j])) { // 숫자 true, 문자 false
                        check(i, j, map[i][j]-'0'); // 현재위치, 지뢰개수
                    }
                }
            }

            ans = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == '*') ans++;
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void check(int x, int y, int cur) {
        for (int i = 0; i < 3; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < 2 && ny >= 0 && ny < n) { // 범위 안에 있고
                if (map[nx][ny] == '#' && cur != 0) { // 넣을 지뢰가 있고 넣을 수 있는 칸
                    cur--; // 지뢰 개수 감소
                    map[nx][ny] = '*'; // 지뢰 표시
                } else if (map[nx][ny] == '*' && cur != 0) { // 지뢰가 이미 있음
                    cur--; // 지뢰 개수만 감소
                } else if (map[nx][ny] == '#' && cur == 0) { // 더 이상 넣을 지뢰가 없음
                    map[nx][ny] = '-'; // # -> - 바꿔주기
                }
            }
        }
    }
}