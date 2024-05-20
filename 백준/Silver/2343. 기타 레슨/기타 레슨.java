import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int [] arr;
    private static int low, high;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 강의의 수
        int m = Integer.parseInt(st.nextToken()); // 블루레이 개수

        st = new StringTokenizer(br.readLine());
        int start = 0, end = 0;
        int[] length = new int[n];
        for (int i = 0; i < n; i++) {
            length[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, length[i]);
            end += length[i];
        }

        while (start <= end) {
            int mid = start+(end-start) / 2;

            int sum = 0;
            int cnt = 0;
            for (int l : length) {
                if (sum+l > mid) {
                    sum = 0;
                    cnt++;
                }
                sum += l;
            }

            if (sum > 0) cnt++;

            if (cnt <= m) end = mid-1;
            else start = mid+1;
        }

        System.out.println(start);
    }
}