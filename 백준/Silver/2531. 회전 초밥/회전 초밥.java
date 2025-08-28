import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n, d, k, c;
    static int[] sushi, eaten;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[n]; // 초밥의 종류
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        bw.append(slide() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int slide() {
        int[] eaten = new int[d+1];
        eaten[c] = 1; // 쿠폰으로 먹을 수 있는 메뉴 먼저 먹기
        int cnt = 1;

        // 0부터 k까지 연속해서 스시 먹기
        for (int i = 0; i < k; i++) {
            if (eaten[sushi[i]] == 0) { // 먹은적 없다면
                cnt++; // 먹은 스시 개수 증가
            }
            eaten[sushi[i]]++;
        }

        int max = cnt;
        // 회전시키면서
        for (int i = 1; i < n; i++) {
            // 슬라이드 이동
            // 앞쪽 스시 못먹음
            // 먹은적 없다면 먹은 스시 -1
            eaten[sushi[i-1]]--;
            if (eaten[sushi[i-1]] == 0) cnt--;
            // 슬라이드 이동
            // 뒤쪽 스시 먹음
            // 먹은적 없다면 새로운 스시니까 +1
            if (eaten[sushi[(i + k - 1) % n]] == 0) cnt++; // 회전하므로 % n
            eaten[sushi[(i + k - 1) % n]]++;

            if (max <= cnt) {
                max = cnt; // 최대 개수 갱신
            }
        }
        return max;
    }
}