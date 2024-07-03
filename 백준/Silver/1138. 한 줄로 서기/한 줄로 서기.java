import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 사람의 수
        int[] line = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= n; j++) { // line
                if (cnt == 0) { // 자기가 제일 큰 경우
                    if (line[j] == 0) { // 줄 선 사람이 없다면 줄서기
                        line[j] = i;
                        break;
                    }
                } else if (line[j] == 0) { // 아직 cnt가 0이 아님 -> 자기보다 큰 사람이 있으므로 다음 칸 가보기
                    cnt--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(line[i]).append(" ");
        }
        System.out.println(sb);
    }
}