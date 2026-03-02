import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int k = Integer.parseInt(st.nextToken()); // 반 등수의 차이

        Queue<Integer>[] que = new Queue[21]; // 이름 길이별로 학생들의 등수를 저장
        for (int i = 0; i < 21; i++) {
            que[i] = new LinkedList<>();
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int len = br.readLine().length();

            // 현재 학생 i와 친구가 될 수 없는 학생들을 제거
            while (!que[len].isEmpty() && que[len].peek() < i-k) {
                que[len].poll();
            }

            ans += que[len].size(); // 현재 가능한 친구 수 더하기
            que[len].add(i); // 현재 학생 추가
        }

        System.out.println(ans);

        br.close();
    }
}