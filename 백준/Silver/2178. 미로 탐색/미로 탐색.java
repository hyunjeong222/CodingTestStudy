import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] visited;
	static int[][] map;
	
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
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		br.close();
		
		bfs(0,0);
		System.out.print(map[N-1][M-1]);
		
	}

	private static void bfs(int x, int y) {
		Queue<dot> queue = new LinkedList<dot>();
		queue.offer(new dot(x, y));
		visited[x][y] = true;
		while (!queue.isEmpty()) {
			dot d_q = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = d_q.x + dx[i]; 
				int ny = d_q.y + dy[i];
                
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if(visited[nx][ny] || map[nx][ny] == 0) continue;
                
				if(map[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new dot(nx,ny));
					map[nx][ny] = map[d_q.x][d_q.y] + 1;
				}
			}
		}
	}

}
