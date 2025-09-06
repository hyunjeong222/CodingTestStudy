import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cmd, x, w;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                x = Integer.parseInt(st.nextToken()); // 사물함 번호
                w = Integer.parseInt(st.nextToken()); // 무게

                map.put(w, x);
            } else {
                w = Integer.parseInt(st.nextToken());
                sb.append(map.get(w)).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}