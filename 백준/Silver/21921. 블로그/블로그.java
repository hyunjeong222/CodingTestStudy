import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 블로그를 시작하고 지난 일수
        int x = Integer.parseInt(st.nextToken()); // x일 동안

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { // 1일차부터 n일차까지 하루 방문자 수 입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int day = 1; // x일 동안 가장 많이 들어온 기간
        for (int i = 0; i < x; i++) sum += arr[i]; // x일마다 방문자수의 합

        int max = sum; // 최대 방문자수
        for (int i = x; i < n; i++) {
            sum -= arr[i-x]; // 첫번째 제외
            sum += arr[i];

            if (max < sum) { // 최댓값 갱신
                day = 0; // 최댓값 갱신되면 날짜 초기화
                day++;
                max = sum;
            } else if (max == sum) { // 같은 기간이면
                day++;
            }
        }

        if (max == 0) { // 최대 방문자 수가 0명
            bw.append("SAD" + "\n");
            bw.flush();
            return;
        }

        bw.append(max + "\n" + day);
        bw.flush();
        bw.close();
        br.close();
    }
}
