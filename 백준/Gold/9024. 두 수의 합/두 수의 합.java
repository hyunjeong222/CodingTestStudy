import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 수의 개수
            k = Integer.parseInt(st.nextToken()); // k에 가까운

            arr = new int[n]; // n개의 정수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int cnt = getCnt(); // 서로 다른 두 정수의 합으로 만들 수 있는 K 에 가장 가까운 조합의 수
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static int getCnt() {
        int cnt = 0;

        int start = 0;
        int end = n-1;
        int min = Integer.MAX_VALUE;
        while (start < end) {
            int sum = arr[start] + arr[end];
            int diff = Math.abs(sum-k);

            if (min >= diff) {
                if (min > diff) cnt = 0;
                min = diff;
                cnt++;
            }

            if (sum >= k) end--;
            else start++;
        }
        return cnt;
    }
}