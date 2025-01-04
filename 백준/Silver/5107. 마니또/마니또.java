import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map;
        int t = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            map = new HashMap<>();
            int num = 1;
            for (int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                String from = st.nextToken();
                String to = st.nextToken();

                if (!map.containsKey(from)) map.put(from, num++);
                if (!map.containsKey(to)) map.put(to, num++);

                list.get(map.get(to)).add(map.get(from));
            }

            checked = new boolean[n+1];
            int cycle = 0;
            for (int i = 1; i <= n; i++) {
                if (!checked[i]) {
                    dfs(i);
                    cycle++;
                }
            }

            sb.append(t++).append(" ").append(cycle).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        checked[start] = true;

        for (int next : list.get(start)) {
            if (!checked[next]) {
                dfs(next);
            }
        }
    }
}