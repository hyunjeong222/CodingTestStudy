import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n; // 총 시험장의 개수
    static int b, c; // 총감독관이 감시할 수 있는 응시자수, 부감독관이 감시할 수 있는 응시자수
    static int[] candidate;
    static long cnt; // 감독관의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        candidate = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            candidate[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cnt += n; // 총감독은 시험장 당 한명
        for (int i = 0; i < n; i++) {
            candidate[i] -= b;
            if (candidate[i] <= 0) continue;
            cnt += candidate[i] / c;
            if (candidate[i] % c != 0) cnt++;
        }
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}