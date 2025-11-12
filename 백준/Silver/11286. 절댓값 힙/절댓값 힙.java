import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine()); // 연산의 개수
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            // x, y의 절댓값
            int abs1 = Math.abs(x);
            int abs2 = Math.abs(y);

            if (abs1 != abs2) return abs1 - abs2; // 절댓값 기준 오름차순
            return x - y; // 절댓값 같으면 실제 값 오름차순
        });
        
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) { // x가 0이라면
                if (pq.isEmpty()) { // 배열이 비어 있는 경우
                    sb.append(0).append("\n"); // 0 출력
                }else { // 절댓값이 가장 작은 값을 출력 후 그 값 제거
                    sb.append(pq.poll()).append("\n");
                }
            }else { // x가 0이 아니라면
                pq.offer(x); // 배열에 값 추가
            }
        }
        
        System.out.println(sb.toString());
        
        br.close();
    }
}