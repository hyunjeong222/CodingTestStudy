import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        // 가로로 몇 번 잘랐는지를 기준으로 둔다.
        long start = 0;
        long end = n/2;
        while (start <= end) {
            long row = start + (end - start) / 2;
            long col = n-row;

            // 자른 색종이 조각의 총 개수
            long total = getTotal(row, col);
            if (total == k) {
                System.out.println("YES");
                return;
            } else if (total > k) { // 더 크게 잘라야 함 -> 범위를 왼쪽으로 이동
                end = row-1;
            } else {
                start = row+1;
            }
        }

        System.out.println("NO");
    }

    private static long getTotal(long row, long col) {
        return (row+1)*(col+1);
    }
}