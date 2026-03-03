import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 참가자 수

        // 특정 위치의 원소를 제거하기 위해 - remove()
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.offer(i);
        }

        long stage = 1;
        int idx = 0;
        while (que.size() != 1) {
            long end = (long) Math.pow(stage, 3);
            idx = (int)((idx+end-1)%que.size()); // 제거될 사람의 번호
            que.remove(idx); // 인덱스 0번부터
            stage++; // 다음 단계
        }

        System.out.println(que.poll());

        br.close();
    }
}