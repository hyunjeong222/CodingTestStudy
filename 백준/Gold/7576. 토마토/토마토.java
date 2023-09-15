import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	
	static int M;
	static int N;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static public class dot {
		int x;
		int y;
		
		public dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int max;
	static Queue<dot> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
        
        q = new LinkedList<dot>();
        
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
					q.offer(new dot(i, j));
				}
				
			}
		}
        
		bfs();
	}
	
	private static void bfs() {
		while (!q.isEmpty()) {
			dot d_q = q.poll();
			
			for (int k = 0; k < 4; k++) {
				int nx = d_q.x + dx[k];
				int ny = d_q.y + dy[k];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				if (map[nx][ny] != 0) continue;
				
				map[nx][ny] = map[d_q.x][d_q.y] + 1;
				q.offer(new dot(nx, ny));
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int l = 0; l < M; l++) {
				if (map[k][l] == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, map[k][l]);
			}
		}
		System.out.println(max-1);
	}
}
