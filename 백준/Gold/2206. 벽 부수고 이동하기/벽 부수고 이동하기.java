import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][][] visited;
	static int n;
	static int m;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static public class dot {
		int x, y;
		int count;
		int isbreak;
		public dot(int x, int y, int count, int isbreak) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.isbreak = isbreak;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		System.out.println(bfs(0,0));
	}

	private static int bfs(int x, int y) {
		Queue<dot> q = new LinkedList<dot>();
		q.offer(new dot(x, y, 1, 0));
		visited[x][y][0] = true;
		visited[x][y][1] = true;

		while (!q.isEmpty()) {
			dot d_q = q.poll();

			if (d_q.x == n - 1 && d_q.y == m - 1) return d_q.count;

			for (int i = 0; i < 4; i++) {
				int nx = d_q.x + dx[i];
				int ny = d_q.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (map[nx][ny] == 0) {
						if (!visited[nx][ny][d_q.isbreak]) {
							q.offer(new dot(nx, ny, d_q.count+1, d_q.isbreak));
							visited[nx][ny][d_q.isbreak] = true;
						}
					} else if (map[nx][ny] == 1) {
						if (d_q.isbreak == 0 && !visited[nx][ny][1]) {
							q.offer(new dot(nx, ny, d_q.count+1, 1));
							visited[nx][ny][1] = true;
						}
					}
				}
			}
		}
		return -1;
	}
}