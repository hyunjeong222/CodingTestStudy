import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static public class pos implements Comparable<pos> {
        int start;
        int end;
        public pos(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(pos o) {
            if (this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        pos[] arr = new pos[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new pos(start, end);
        }
        Arrays.sort(arr);

        int ans = 0;
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].start >= prev) {
                prev = arr[i].end;
                ans++;
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}