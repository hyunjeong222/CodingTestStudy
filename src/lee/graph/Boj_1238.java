package lee.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 파티 (GOL3)
 * 시간 : 508 ms
 * 메모리: 74452 KB
 * 링크 : https://www.acmicpc.net/problem/1238
 * */
public class Boj_1238 {
	static int N, M, X, max;
	static boolean[] visited;
	static int[] minCost;
	static ArrayList<ArrayList<Node>> nodeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 숫자로 구분된 각각의 마을
		M = Integer.parseInt(st.nextToken()); // M개의 단방향 도로들
		X = Integer.parseInt(st.nextToken()); // 파티 장소
		nodeList = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			nodeList.add(new ArrayList<>());
		}//for
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList.get(a).add(new Node(b, cost));
		}//while
		
		getMin();
		System.out.print(max);
	}//main

	private static void getMin() {
		max = 0;
		int go, back;
		for(int i=1; i<=N; i++) {			
			go = dijkstra(i, X);
			back = dijkstra(X, i);
			max = Math.max(max, go+back);
		}//for
		
	}//getMin

	private static int dijkstra(int start, int end) {
		init(start);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		Node cur;
		int to, cost;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			to = cur.to;
			cost = cur.cost;
			if(to == end) return minCost[end];
			if(visited[to]) continue;
			visited[to] = true;
			for(Node next : nodeList.get(to)) {
				if(!visited[next.to] && minCost[next.to] > cost + next.cost) {
					minCost[next.to] = cost + next.cost;
					pq.offer(new Node(next.to, minCost[next.to]));
				}
			}//for
		}//while

		return minCost[end];
	}//dijkstra

	private static void init(int start) {
		visited = new boolean[N+1];
		minCost = new int[N+1];
		Arrays.fill(minCost, 987654321);	
		minCost[start] = 0;
	}//init
	
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}

}//class
