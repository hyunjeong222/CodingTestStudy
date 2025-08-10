import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 개수
        int m = Integer.parseInt(st.nextToken()); // 리프 노드 개수 (자식의 개수)

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        if (m == 2) {
            cnt = 1;
        }

        int node = 0;
        for (int i = 1; i < n; i++) {
            if (m > cnt) {
                sb.append(0).append(" ").append(i).append("\n");
                cnt++;
            } else {
                sb.append(node).append(" ").append(i).append("\n");
            }

            node = i;
        }

        System.out.println(sb.toString());

        br.close();
    }
}