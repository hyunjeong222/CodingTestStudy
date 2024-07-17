import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Deque<Integer> deque;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            char[] p = br.readLine().toCharArray(); // 수행할 함수 명령

            int n = Integer.parseInt(br.readLine()); // 배열에 들이있는 수의 개수
            String str = br.readLine(); // 배열의 형태

            // 숫자만 추출해서 deque에 넣기
            getNumber(str);
            // 명령 수행
            solve(p);
        }

        System.out.println(sb);
    }

    private static void getNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+"); // 숫자만 추출
        Matcher matcher = pattern.matcher(str);

        deque = new ArrayDeque<>();

        while (matcher.find()) {
            deque.offer(Integer.parseInt(matcher.group()));
        }
    }

    private static void solve(char[] p) {
        boolean reverseFlag = false;

        for (char order : p) {
            if (order == 'R') { // 뒤집기
                reverseFlag = !reverseFlag;
            }else { // D : 첫번째 숫자 버리기
                if (deque.isEmpty()) { // 배열이 비어있는데 D를 사용한 경우 에러
                    sb.append("error").append("\n");
                    return;
                }
                if (reverseFlag) deque.removeLast();
                else deque.removeFirst();
            }
        }

        sb.append("[");
        while (!deque.isEmpty()) {
            sb.append(reverseFlag ? deque.removeLast() : deque.removeFirst());
            if (deque.size() != 0) sb.append(",");
        }
        sb.append("]").append("\n");
    }
}