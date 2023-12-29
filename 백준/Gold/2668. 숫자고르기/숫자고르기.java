import java.io.*;
import java.util.ArrayList;

public class Main {
    static int[] arr;
    static boolean[] checked;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        list = new ArrayList<>();
        checked = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            checked[i] = true;
            dfs(i, i);
            checked[i] = false;
        }
        bw.append(list.size() + "\n");
        for (int i = 0; i < list.size(); i++) {
            bw.append(list.get(i) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int target) {
        if (!checked[arr[start]]) {
            checked[arr[start]] = true;
            dfs(arr[start], target);
            checked[arr[start]] = false;
        }
        if (arr[start] == target) list.add(arr[start]);
    }
}