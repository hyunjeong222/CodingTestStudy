import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int[] time, indgree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<>());
        }

        time = new int[26];
        indgree = new int[26];

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] tmpArr = line.split(" ");
            int tmp = tmpArr[0].charAt(0) - 'A';
            time[tmp] = Integer.parseInt(tmpArr[1]);

            if (tmpArr.length > 2) {
                for (char c : tmpArr[2].toCharArray()) {
                    int idx = c - 'A'; // 먼저 완료되어야 하는 직업
                    list.get(idx).add(tmp);
                    indgree[tmp]++;
                }
            }

            // System.out.println(Arrays.toString(tmp));
        }

        int ans = topologicalSort();

        System.out.println(ans);

    }

    private static int topologicalSort() {
        Queue<Integer> que = new LinkedList<>();
        int[] result = new int[26];
        int total = 0;

        for (int i = 0; i < 26; i++) {
            if (indgree[i] == 0 && time[i] != 0) {
                result[i] = time[i];
                total = Math.max(total, result[i]);
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                indgree[next]--;
                result[next] = Math.max(result[next], result[now]+time[next]);
                total = Math.max(total, result[next]);

                if (indgree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        return total;
    }
}