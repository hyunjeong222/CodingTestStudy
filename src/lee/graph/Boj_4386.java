package lee.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 별자리 만들기(GOL3)
 * 시간 : 120 ms
 * 메모리: 13264 KB
 * 링크 : https://www.acmicpc.net/problem/4386
 * */
public class Boj_4386 {
	static int N;
	static Star[] stars;
	static ArrayList<ArrayList<Node>> nodeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 별의 개수
		stars = new Star[N];
		StringTokenizer st;
		nodeList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
			nodeList.add(new ArrayList<>());
		}//for
		br.close();
		
		for(int from=0; from<N; from++) {
			for(int to=from+1; to<N; to++) {
				double cost = getDistance(stars[from], stars[to]);
				nodeList.get(from).add(new Node(to, cost));
				nodeList.get(to).add(new Node(from, cost));
			}//for to
		}//for from
		
		double min = getMin();
		System.out.printf("%.2f", min);
	}//main
	
	private static double getMin() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		pq.offer(new Node(0, 0.0));
		
		int to;
		double cost, min = 0;
		Node cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			to = cur.to;
			cost = cur.cost;
			if(visited[to]) continue;
			visited[to] = true;
			min += cost;
			
			for(Node next : nodeList.get(to)) {
				if(visited[next.to]) continue;
				pq.offer(new Node(next.to, next.cost));
			}//for
		}//while
		
		return min;
	}//getMin

	static double getDistance(Star from, Star to) {
		return Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2)); 
	}//getDistance
	
	static class Node implements Comparable<Node>{
		int to;
		double cost;
		public Node(int to, double cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return Double.compare(this.cost, n.cost);
		}
	}//Node
	
	static class Star{
		int idx;
		double x;
		double y;
		public Star(int idx, double x, double y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}
	}//Star

}//class
