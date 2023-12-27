import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer> list;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        dfs(list, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(ArrayList<Integer> list, int e) {
        if (list.size() <= 2) {
            ans = Math.max(ans, e);
            return;
        }

        for (int i = 1; i < list.size()-1; i++) {
            int tmp = list.get(i);
            int value = list.get(i-1) * list.get(i+1);
            list.remove(i);
            dfs(list, e+value);
            list.add(i, tmp);
        }
    }
}