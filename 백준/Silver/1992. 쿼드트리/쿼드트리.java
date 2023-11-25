import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 영상의 크기
        map = new int[n][n]; // 영상
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        partition(0, 0, n);
        System.out.println(sb.toString());
    }

    private static void partition(int x, int y, int size) {
        if (colorCheck(x, y, size)) {
            sb.append(map[x][y]);
            return;
        }

        int newSize = size/2;
        sb.append("(");
        partition(x, y, newSize);
        partition(x, y+newSize, newSize);
        partition(x+newSize, y, newSize);
        partition(x+newSize, y+newSize, newSize);
        sb.append(")");
    }

    private static boolean colorCheck(int x, int y, int size) {
        int color = map[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != color) return false;
            }
        }
        return true;
    }
}