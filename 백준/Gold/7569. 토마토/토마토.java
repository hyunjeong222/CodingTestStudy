import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][][] map;
	
	static int M;
	static int N;
	static int H;
	
	static int[] dx = {-1,0,1,0,0,0};
	static int[] dy = {0,-1,0,1,0,0};
	static int[] dz = {0,0,0,0,-1,1};
	
	static public class dot {
		int z;
		int x;
		int y;
		
		public dot(int z, int x, int y) {
			this.z = z;
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
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		q = new LinkedList<dot>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						q.offer(new dot(i, j, k));
					}
				}
			}
		}
		bfs();
	}
	
	private static void bfs() {
		while (!q.isEmpty()) {
			dot d_q = q.poll();
			
			for (int k = 0; k < 6; k++) {
				int nx = d_q.x + dx[k];
				int ny = d_q.y + dy[k];
				int nz = d_q.z + dz[k];
				
				if (nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H) continue;
				
				if (map[nz][nx][ny] != 0) continue;
				
				map[nz][nx][ny] = map[d_q.z][d_q.x][d_q.y] + 1;
				q.offer(new dot(nz, nx, ny));
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					max = Math.max(max, map[i][j][k]);
				}
			}
		}
		System.out.println(max-1);
	}
}
