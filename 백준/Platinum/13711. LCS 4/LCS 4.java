import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] res, lis;
    static final int INF = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] origin = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }
        int[] index = new int[n];
        int num;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num = Integer.parseInt(st.nextToken());
            index[num-1] = i;
        }
        res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = index[origin[i]-1]+1;
        }

        int lisCnt = getLis();

        System.out.println(lisCnt);
    }

    private static int getLis() {
        int len = 0, idx;
        lis = new int[n+1];
        Arrays.fill(lis, INF);
        lis[0] = 0;
        for (int i = 0; i < n; i++) {
            idx = binarySearch(0, len+1, res[i]);
            lis[idx] = res[i];
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