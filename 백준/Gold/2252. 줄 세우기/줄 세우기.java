import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생의 수
        int m = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] edgeCout = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            graph.get(num1).add(num2);
            edgeCout[num2]++;
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (edgeCout[i] == 0) que.offer(i);
        }
        while (!que.isEmpty()) {
            int node = que.poll();

            sb.append(node).append(" ");

            List<Integer> list = graph.get(node);
            for (int i = 0; i < list.size(); i++) {
                edgeCout[list.get(i)]--;
                if (edgeCout[list.get(i)] == 0) {
                    que.offer(list.get(i));
                }
            }
        }
        System.out.println(sb);
    }
}