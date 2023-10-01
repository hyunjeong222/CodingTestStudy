import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] vidited = new int[100001];
	
	static int N;
	static int K;
	
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (N == K) { 
			System.out.println(0);
		} else {
			bfs(N);
		}
		br.close();
	}
	
	private static void bfs(int N) {
		q = new LinkedList<Integer>();
		q.offer(N);
		
		vidited[N] = 1;
		
		while (!q.isEmpty()) {
			int location = q.poll();
			
			for (int i = 0; i < 3; i++) {
				int next;
				
				if (i == 0) {
					next = location - 1;
				} else if (i == 1) {
					next = location + 1;
				} else {
					next = location * 2;
				}
				
				if (next == K) {
					System.out.println(vidited[location]);
					return;
				}
				
				if (next >= 0 && next < vidited.length && vidited[next] == 0) {
					q.offer(next);
					vidited[next] = vidited[location] + 1;
				}
			}
		}
	}
}