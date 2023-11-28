import java.io.*;

public class Main {
    static int n;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        partition(0, 0, n, false);
        for (char[] line : arr) {
            bw.write(line);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
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

        for (int i = x; i < x+size; i+=newSize) {
            for (int j = y; j < y+size; j+=newSize) {
                if (i == x+newSize && j == y+newSize) {
                    partition(i, j, newSize, true);
                } else {
                    partition(i, j, newSize, false);
                }
            }
        }
    }
}