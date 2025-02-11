import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] stock;
    static int[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int tIdx = 1;
        while (t --> 0) {
            sb.append("Case #").append(tIdx++).append("\n");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 앞으로 주가를 알 수 있는 날 수
            k = Integer.parseInt(st.nextToken()); // 거래의 회수

            stock = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }
            memo = new int[n+1];
            int len = 0; int idx = 0;
            for (int i = 0; i < n; i++) {
                if (stock[i] > memo[len]) {
                    len += 1;
                    memo[len] = stock[i];
                } else {
                    idx = binarySearch(0, len, stock[i]);
                    memo[idx] = stock[i];
                }
            }

            if (len >= k) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int start, int end, int target) {
        int mid;
        while (start < end) {
            mid = start+(end-start)/2;
            if (memo[mid] < target) {
                start = mid+1;
            } else end = mid;
        }
        return end;
    }
}