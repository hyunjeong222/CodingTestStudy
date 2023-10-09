import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int[][] map;
	static int cnt = 0;
	static int node, line;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		node = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		line = Integer.parseInt(st.nextToken());

		map = new int[node+1][node+1];
		visited = new boolean[node+1];

		for (int i = 0; i < line; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			map[a][b] = map[b][a] = 1;
		}
		br.close();
		dfs(1);
		System.out.println(cnt-1);
	}

	private static void dfs(int i) {
		visited[i] = true;
		cnt++;
		for (int j = 0; j <= node; j++) {
			if (map[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}

	}

}