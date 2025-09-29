import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String group = br.readLine();
            int size = Integer.parseInt(br.readLine());
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add(br.readLine());
            }
            map.put(group, list);
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            int cmd = Integer.parseInt(br.readLine());

            if (cmd == 1) {
                for (String group : map.keySet()) {
                    if (map.get(group).contains(name)) {
                        sb.append(group).append("\n");
                        break;
                    }
                }
            } else { // 0
                ArrayList<String> list = map.get(name);
                Collections.sort(list);
                for (String member : list) {
                    sb.append(member).append("\n");
                }
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}