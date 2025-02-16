import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashMap<String, String> map = new HashMap<>();
        String name, state;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            state = st.nextToken();

            if (map.containsKey(name)) {
                map.remove(name);
            } else {
                map.put(name, state);
            }
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("\n");
        }

        System.out.println(sb.toString());
    }
}