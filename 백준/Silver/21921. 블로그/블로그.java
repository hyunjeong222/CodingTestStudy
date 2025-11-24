import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 블로그 운영일 수
        int x = Integer.parseInt(st.nextToken()); // 관찰할 기간 길이

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 하루 방문자 수 입력
        }

        int sum = 0; // 현재 X일 구간의 방문자 합
        int cnt = 1; // 최대 방문자 수를 기록한 기간의 개수
        
        // 1일부터 x일까지 방문자수의 합
        for (int i = 0; i < x; i++) sum += arr[i];

        int max = sum; // 현재까지의 최대 방문자 수
        
        for (int i = x; i < n; i++) {
            sum -= arr[i-x]; // 빠지는 값 제거
            sum += arr[i]; // 새로 포함되는 값 추가

            if (sum > max) { // 더 큰 값 발견 시
                max = sum;
                cnt = 1; // 기간 개수 초기화 후 1로 설정
            } else if (sum == max) { 
                cnt++; // 동일한 최대값인 기간 발견
            }
        }

        if (max == 0) { // 모든 구간의 방문자 수가 0인 경우
            bw.write("SAD" + "\n");
        } else {
            bw.write(max + "\n" + cnt);
        }
        
        bw.flush();
        
        bw.close();
        br.close();
    }
}