import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] check;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int v = scanner.nextInt();
		
		check = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			check[x][y] = check[y][x] = 1;
		}
		
		dfs(v);
		visited = new boolean[n+1];
		System.out.println();
		bfs(v);
	}
	
	private static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		if (v == check.length) {
			return;
		}
		for (int i = 1; i < check.length; i++) {
			if (check[v][i] == 1 && visited[i] == false) {
				dfs(i);
			}
		}
		
	}
	
	private static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(v);
		visited[v] = true;
		System.out.print(v + " ");
		
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			for (int i = 1; i < check.length; i++) {
				if (check[temp][i] == 1 && visited[i] == false) {
					queue.add(i);
					visited[i] = true;
					System.out.print(i + " ");
				}
			}
		}
	}
}
