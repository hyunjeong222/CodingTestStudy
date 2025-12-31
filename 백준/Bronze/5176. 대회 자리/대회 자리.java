import java.io.*;
import java.util.StringTokenizer;

// 백준 5176번 문제
public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader, BufferedWriter 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 0; t < K; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken()); // 참가자 수
            int M = Integer.parseInt(st.nextToken()); // 자리 수

            boolean[] seats = new boolean[M + 1]; // 자리 선점 체크 (1부터 M까지)

            int cannotSitCount = 0; // 앉지 못하는 사람 수

            for (int i = 0; i < P; i++) {
                int preferredSeat = Integer.parseInt(br.readLine());

                // 만약 선점된 자리가 아니면 앉기, 아니면 앉지 못함
                if (!seats[preferredSeat]) {
                    seats[preferredSeat] = true;
                } else {
                    cannotSitCount++;
                }
            }

            bw.write(cannotSitCount + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}