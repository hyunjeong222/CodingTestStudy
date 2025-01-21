import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()); // 파의 개수
        int c = Integer.parseInt(st.nextToken()); // 파닭의 수

        int[] length = new int[s];
        int max = 0;
        long sum = 0;
        for (int i = 0; i < s; i++) {
            length[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, length[i]);
            sum += length[i];
        }

        long start = 1;
        long end = max;
        int cnt;
        while (start <= end) {
            long mid = start+(end-start) / 2;
            cnt = 0;

            for (int i = 0; i < s; i++) {
                cnt += length[i]/mid;
            }

            // 한 파의 양을 최대한 많이 넣으려고 한다.
            if (cnt >= c) { // 파를 더 길게
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        System.out.println(sum-(c*end));
    }
}