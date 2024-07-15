import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        long[] courses = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); 
            long courseMask = 0;
            for (int j = 0; j < k; j++) {
                int t = Integer.parseInt(st.nextToken());
                courseMask |= ((long)1 << (t-1));
            }
            courses[i] = courseMask;
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            long studentMask = 0;
            for (int j = 0; j < p; j++) {
                long q = Integer.parseInt(st.nextToken());
                studentMask |= ((long)1 << (q-1));
            }
            int cnt = 0;
            for (long course : courses) {
                // course & bitmask : 두 비트간의 공통 요소
                if ((course & studentMask) == course) {
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}