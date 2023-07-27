package lee.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25757
public class Boj_25757 {
	static int size; // 게임 인원 수
	static char game; // 게임
	static Map<Character, Integer> cntMap; // 게임에 필요한 인원수
	static Map<String, Integer> playerMap; // 게임 신청 플레이어들
	
	/**
     * 풀이 시간 : 1 초
     * 시간 : 248ms
     * 메모리: 25312KB
     * */

	public static void main(String[] args) throws Exception {
		cntMap = new HashMap<>();
		cntMap.put('Y', 2); // 윷놀이 
		cntMap.put('F', 3); // 같은 그림 찾기 
		cntMap.put('O', 4); // 원카드
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		game = st.nextToken().charAt(0);
		size = cntMap.get(game);
		
		int playCnt = 0, cnt=1; // 게임 횟수, 인원
		playerMap = new HashMap<>(); // 게임 신청한 사람들 
		while(N-->0) {
			String key = br.readLine();
			if( playerMap.containsKey(key)) continue; // 이미 게임했으면 ㄴㄴ
			playerMap.put(key, 1);
			cnt++; // 인원 증가
			if(cnt % size == 0) { // 인원 다 채움
				playCnt++; // 게임 횟수 증가
				cnt = 1; // 인원 초기화 (임스 1명)
			}
		}//while
		br.close();

		System.out.print(playCnt);
	}//main

}//class

/*

7 Y
lms0806
lms0806
exponentiale
lms0806
jthis
lms0806
leo020630

*/
