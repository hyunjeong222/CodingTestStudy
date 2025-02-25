import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] books, lis;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        books = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }

        int lisCnt = getLis();

        System.out.println(n-lisCnt);
    }

    public static int getLis() {
        lis = new int[n+1];
        int len = 0, idx;

        Arrays.fill(lis, INF);
        lis[0] = 0;

        for (int i = 0; i < n; i++) {
            idx = binarySearch(0, len+1, books[i]);
            lis[idx] = books[i];
            len = Math.max(len, idx);
        }

        return len;
    }

    private static int binarySearch(int start, int end, int target) {
        int mid;
        while (start < end) {
            mid = start + (end-start) / 2;
            if (lis[mid] < target) start = mid+1;
            else end = mid;
        }
        
        return end;
    }
}