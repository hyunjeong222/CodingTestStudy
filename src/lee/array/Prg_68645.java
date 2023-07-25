package lee.array;

public class Prg_68645 {
	static int[] dr = {1, 0, -1}; // 하 우 상
	static int[] dc = {0, 1, -1}; // 하 우 상
	
	/**
	 * 문제 이름(난이도) : 월간 코드 챌린지 시즌1 > 삼각 달팽이 (LV2)
     * 시간 : 29.87 ms
     * 메모리: 123 MB
     * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/68645
     * */
	
	public int[] solution(int n) {
		int len = 0; // 배열 크기		        
        int[][] tmp = new int[n][]; // 삼각 달팽이 임시 배열
        
        for(int i=0; i<n; i++) {
        	tmp[i] = new int[i+1];
        	len += i+1;
        }//for
        
        int cnt = 0; // 횟수
        int r=0, c=0; // 행, 열
        tmp[0][0] = 1; // 첫 시작은 1부터
        while(cnt < n) {        	
        	for(int i=0; i<n-cnt; i++) {
        		int nr = r + dr[cnt%3]; // 다음 행
        		int nc = c + dc[cnt%3]; // 다음 열
        		if(rangeCheck(nr, nc, n)) continue;
        		tmp[nr][nc] += tmp[r][c]+1; // 1씩 증가해서 채우기
        		r = nr;
        		c = nc;
        	}//for 
        	cnt++;
        }//while

        int[] answer = new int[len];
        fillArray(answer, tmp);
        return answer;
    }//solution

	// 배열 채우기
	private void fillArray(int[] answer, int[][] tmp) {
		int idx = 0;
		for(int[] arr : tmp) {
			for(int n : arr) {
				answer[idx++] = n;
			}//for
		}//for
		
	}//fillArray

	// 범위 체크
	private boolean rangeCheck(int r, int c, int n) {
		return r < 0 || r >= n || c < 0 || c >= n;
	}//rangeCheck


}//class