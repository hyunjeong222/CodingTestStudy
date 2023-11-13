import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int cnt = 1;
        while (a != b) {
            if (b < a) {
                bw.append(-1 + "\n");
                bw.flush();
                return;
            }

            if (b % 10 == 1) b /= 10;
            else if (b % 2 == 0) b /= 2;
            else {
                bw.append(-1 + "\n");
                bw.flush();
                return;
            }
            cnt++;
        }
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}