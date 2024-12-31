import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int p, q;
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        System.out.println(solve(n));
    }

    private static long solve(long num) {
        if (num == 0) return 1;
        if (map.containsKey(num)) return map.get(num);

        long a = (long)Math.floor(num/p);
        long b = (long)Math.floor(num/q);

        map.put(num, solve(a)+solve(b));

        return map.get(num);
    }
}