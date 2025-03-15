import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] weight;
    static boolean[] checked;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 농장에 있는 소들의 수
        m = Integer.parseInt(st.nextToken()); // 선별할 소의 수

        weight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[n];
        dfs(0, 0, 0);

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        if (list.size() == 0) sb.append(-1);
        else {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int depth, int sum, int start) {
        if (depth == m) {
            if (isPrime(sum)) {
                set.add(sum);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(depth+1, sum+weight[i], i+1);
                checked[i] = false;
            }
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i ==0) return false;
        }

        return true;
    }
}