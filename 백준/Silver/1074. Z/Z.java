import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, r, c;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 2N × 2N
        // r행 c열을 몇 번째로 방문
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = (int)Math.pow(2, n);

        partition(0, 0, size);
        System.out.println(ans);
    }

    private static void partition(int x, int y, int size) {
        if (size == 1) return;

        int newSize = size/2;
        if (r < x+newSize && c < y+newSize) partition(x, y, newSize);
        if (r < x+newSize && c >= y+newSize) {
            ans += (size*size) / 4;
            partition(x, y+newSize, newSize);
        }
        if (r >= x+newSize && c < y+newSize) {
            ans += ((size*size) / 4) * 2;
            partition(x+newSize, y, newSize);
        }
        if (r >= x+newSize && c >= y+newSize) {
            ans += ((size*size) / 4) * 3;
            partition(x+newSize, y+newSize, newSize);
        }
    }
}