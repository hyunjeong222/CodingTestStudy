import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int k,v,e;
	static int p1,p2;
	static ArrayList<Integer>[] list;
	static int[] vidited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			vidited = new int[v+1];
			list = new ArrayList[v+1];
			
			for (int j = 0; j < v+1; j++) {
				list[j] = new ArrayList<Integer>();
			}
			
			for (int j = 0; j < e; j++) {
				st = new StringTokenizer(br.readLine());
				p1 = Integer.parseInt(st.nextToken());
				p2 = Integer.parseInt(st.nextToken());
				
				list[p1].add(p2);
				list[p2].add(p1);
			}
			bfs();
		}
	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i < v+1; i++) {
			if (vidited[i] == 0) {
				q.offer(i);
				vidited[i] = 1;
			}
			
			while (!q.isEmpty()) {
				int now = q.poll();
				
				for (int j = 0; j < list[now].size(); j++) {
					if (vidited[list[now].get(j)] == 0) {
						q.offer(list[now].get(j));
					}
					
					if (vidited[list[now].get(j)] == vidited[now]) {
						System.out.println("NO");
						return;
					}
					
					if (vidited[now] == 1 && vidited[list[now].get(j)] == 0) {
						vidited[list[now].get(j)] = 2;
					} else if (vidited[now] == 2 && vidited[list[now].get(j)] == 0) {
						vidited[list[now].get(j)] = 1;
					}
				}
			}
		}
		System.out.println("YES");
	}
}