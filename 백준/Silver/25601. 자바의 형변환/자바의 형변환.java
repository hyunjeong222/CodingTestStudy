import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static HashMap<String, ArrayList<String>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            map.get(a).add(b);
        }
        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        System.out.println((dfs(a,b)||dfs(b,a) ? 1 : 0));
    }

    private static boolean dfs(String a, String b) {
        if (a.equals(b)) return true;
        if (map.get(a) == null) return false;
        for (String next : map.get(a)) {
            if (dfs(next, b)) return true;
        }
        return false;
    }
}