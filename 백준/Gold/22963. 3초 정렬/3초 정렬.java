import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] A, length;
    static final int MAX = 1000000000;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 수열의 길이
        A = new int[n+1]; // 수열
        length = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        A[n] = MAX;

        // 연산 횟수 구하기
        int lisCnt = getLis();
        int k = n - lisCnt; // 연산 횟수

        // 3번의 연산으로 오름차순을 만들 수 없음
        if (k > 3) {
            System.out.println("NO");
            return;
        }

        // 3번의 연산으로 오름차순을 만들 수 있음
        StringBuilder sb = new StringBuilder();
        sb.append("YES").append("\n").append(k).append("\n");

        int cnt = lisCnt;
        int[] lis = new int[n];
        for (int i = n-1; i >= 0; i--) {
            if (length[i] == cnt) {
                lis[cnt] = A[i];
                cnt--;
            }
        }

        cnt = lisCnt;
        for (int i = n-1; i >= 0; i--) {
            if (A[i] == lis[cnt]) {
                cnt--;
                continue;
            }

            int x = A[i+1];
            sb.append(i+1).append(" ").append(x).append("\n");
            A[i] = x;
        }

        // System.out.println(Arrays.toString(lis));

        System.out.println(sb.toString());
    }

    private static int getLis() {
        int len = 0, idx;
        int[] lis = new int[n+1];

        Arrays.fill(lis, INF);
        lis[0] = 0;

        for (int i = 0; i < n; i++) {
            idx = binarySearch(0, len+1, A[i], lis);
            lis[idx] = A[i];
            length[i] = idx;
            len = Math.max(len, idx);
        }

        return len;
    }

    private static int binarySearch(int start, int end, int target, int[] lis) {
        int mid;
        while (start < end) {
            mid = start + (end-start) / 2;
            if (lis[mid] <= target) start = mid+1;
            else end = mid;
        }
        return end;
    }
}