import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] adjList = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
        }
        
        int[] save = new int[n + 1];
        int max = 0;
        for(int i = 1; i <= n; i++) {
        	List<Integer> same = new ArrayList<>();
        	same.add(i);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            boolean[] visited = new boolean[n + 1];
            visited[i] = true;
            int count = 0;
            while(!queue.isEmpty()) {
                int now = queue.poll();
                count++;
                for(int next : adjList[now]) {
                    if(visited[next]) continue;
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            save[i] = count;
            max = Math.max(max, count);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
        	if(save[i] == max) {
        		sb.append(i).append(' ');
        	}
        }
        System.out.println(sb);
    }
}