import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 아이들의 수
        int m = Integer.parseInt(st.nextToken()); // 색상의 수

        int start = 1;
        int end = 0;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, arr[i]);
        }

        while (start <= end) {
            int mid = start + (end-start) / 2;

            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum += arr[i] / mid;

                if (arr[i] % mid != 0) sum++;
            }

            if (sum > n) start = mid+1; // 보석을 못받는 사람 있을 수 있음, 보석을 나눠줄 수 없는 경우
            else end = mid-1; // 나눠줄 수 있는 경우
        }

        System.out.println(start);
    }
}