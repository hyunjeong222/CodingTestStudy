import java.io.*;

public class Main {
    static int n;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        partition(0, 0, n, false);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void partition(int x, int y, int size, boolean blank) {
        if (blank) {
            for (int i = x; i < x+size; i++) {
                for (int j = y; j < y+size; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            arr[x][y] = '*';
            return;
        }

        int newSize = size/3;
        int cnt = 0;
        for (int i = x; i < x+size; i+=newSize) {
            for (int j = y; j < y+size; j+=newSize) {
                cnt++;
                if (cnt == 5) partition(i, j, newSize, true);
                else partition(i, j, newSize, false);
            }
        }
    }
}