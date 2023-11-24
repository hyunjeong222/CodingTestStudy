import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int white = 0;
    static int blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 종이 한 변의 길이
        map = new int[n][n]; // 전체 종이, 하얀색 0 파랑색 1
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        partition(0, 0, n);
        bw.append(white + "\n" + blue);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void partition(int x, int y, int size) {
        if (checkColor(x, y, size)) {
            if (map[x][y] == 1) blue++;
            else white++;
            return;
        }

        int newSize = size/2;
        partition(x, y, newSize);
        partition(x+newSize, y, newSize);
        partition(x, y+newSize, newSize);
        partition(x+newSize, y+newSize, newSize);
    }

    private static boolean checkColor(int x, int y, int size) {
        int color = map[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (color != map[i][j]) return false;
            }
        }
        return true;
    }
}