import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> map = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map.put(st.nextToken(), 0);
        }

        String str;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                str = st.nextToken();
                map.put(str, map.getOrDefault(str, 0)+1);
            }
        }
        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });

        for (String key : keySet) {
            sb.append(key).append(" ").append(map.get(key)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}