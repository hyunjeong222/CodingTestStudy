import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 배열 a 크기
        int m = Integer.parseInt(st.nextToken()); // 배열 b 크기

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int p1 = 0;
        int p2 = 0;
        while (p1 < n && p2 < m) {
            if (a[p1] < b[p2]) {
                sb.append(a[p1]).append(" ");
                p1++;
            } else {
                sb.append(b[p2]).append(" ");
                p2++;
            }
        }
        
        while (p1 < n) {
            sb.append(a[p1]).append(" ");
            p1++;
        }
        while (p2 < m) {
            sb.append(b[p2]).append(" ");
            p2++;
        }

        System.out.println(sb.toString());
        br.close();
    }
}