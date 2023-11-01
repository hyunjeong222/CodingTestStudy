import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 배열 a 크기
        int m = Integer.parseInt(st.nextToken()); // 배열 b 크기

        int[]  a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < n && p2 < m) {
            if (a[p1] < b[p2]) list.add(a[p1++]);
            else list.add(b[p2++]);
        }
        while (p1 < n) list.add(a[p1++]);
        while (p2 < m) list.add(b[p2++]);

        for (int i = 0; i < list.size(); i++) {
            bw.append(list.get(i) + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}