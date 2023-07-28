package lee.collection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 싸지방에 간 준하 (GOL3)
 * 시간 : 952 ms
 * 메모리: 67868 KB
 * 링크 : https://www.acmicpc.net/problem/12764
 * */
public class BOJ_12764 {
	// 대기중인 사람들
	static PriorityQueue<Time> wait; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine()); // 사람의 수
		
		wait = new PriorityQueue<>();
		StringTokenizer st;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			wait.offer(new Time(start, end));
		}//while
		br.close();

		getCnt();
	}//main
	
	private static void getCnt() {		
		int[] computer = new int[100000]; // 컴퓨터
		PriorityQueue<Info> inUse = new PriorityQueue<>(); // 사용중
		// 사용 가능 
		PriorityQueue<Info> available = new PriorityQueue<>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				return o1.num - o2.num;
			}
		});
		
		int cnt = 0; // 컴퓨터 수		
		Time cur;
		Info use;
		while(!wait.isEmpty()) {
			// 현재 컴퓨터 시간이 된 사람
			cur = wait.poll();
			// 사용중인 사람중에 종료시간이 된 사람이 있다면
			while(!inUse.isEmpty() && inUse.peek().end <= cur.start) {
				// 그 사람 사용 종료, 사용가능중인 컴퓨터 추가
				available.offer(inUse.poll());
			}
			// 사용 가능한 컴퓨터가 있다면
			if(!available.isEmpty()) {
				use = available.poll();
				use.end = cur.end;
				inUse.offer(use);
				computer[use.num]++;
			}else {
				// 사용 가능한 컴퓨터가 없다면 새로운 좌석에 앉음.
				computer[cnt]++;
				inUse.offer(new Info(cnt++, cur.end));
			}
		}//while
		
		StringBuilder answer = new StringBuilder();
		answer.append(cnt).append("\n");
		for(int i=0; i<cnt; i++) {
			answer.append(computer[i]).append(" ");
		}//for
		
		System.out.print(answer);		
	}//sit
	
	static class Info implements Comparable<Info> {
		int num; // 컴퓨터 좌석 번호
		int end; // 끝나는 시간
		public Info(int num, int end) {
			this.num = num;
			this.end = end;
		}
		@Override
		public int compareTo(Info i) {
			// 끝나는 시간으로 오름차순
			return this.end - i.end;
		}
		@Override
		public String toString() {
			return "[num=" + num + ", end=" + end + "]";
		}		
	}//Info

	static class Time implements Comparable<Time>{
		int start; // 시작시간
		int end;   // 종료시간
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Time t) {
			// 시작 시간으로 오름차순
			return this.start - t.start;
		}
		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
	}//Time

}//class
/*

5
20 50
10 100
30 120
60 110
80 90

*/