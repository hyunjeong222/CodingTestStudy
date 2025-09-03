import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());

                if (cmd == 'I') {
                    map.put(value, map.getOrDefault(value, 0)+1);
                } else { // D
                    if (map.size() == 0) continue;
                    else {
                        // 1 : 최댓값을 삭제, -1 : 최솟값을 삭제
                        int num = (value == 1) ? map.lastKey() : map.firstKey();
                        // num의 개수를 1 줄이고 (map.put(num, map.get(num) - 1))
                        // 갱신 전 값이 1이었는지 확인 (== 1)
                        // map.put(...) 은 갱신 전의 값(oldValue) 를 반환
                        if (map.put(num, map.get(num)-1) == 1) {
                            map.remove(num);
                        }
                    }
                }
            }

            if (map.isEmpty()) sb.append("EMPTY").append('\n');
            else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}