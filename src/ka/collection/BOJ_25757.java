package ka.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 임스와 함께하는 미니게임(실버5)
 * 시간 : 280ms
 * 메모리: 26900KB
 * 링크 : https://www.acmicpc.net/problem/25757
 * */
public class BOJ_25757 {
    public static void main(String[] args) throws IOException {
        // 입력 받기 위해 버퍼 리더 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 함께 플레이하고자 하는 사람 수
        int n = Integer.parseInt(st.nextToken());

        // 윷놀이, 같은 그림 찾기, 원카드 최대 인원수 구하기
        String sort = st.nextToken();
        int maxPlayer = sort.equals("Y") ? 2 : sort.equals("F") ? 3 : 4;

        // 중복 제거를 위한 Set
        Set<String> playedNickname = new HashSet<>();

        // 임스 1명이 기본 플레이어이므로 1명 부터 시작
        int playerCnt = 1;
        int playGameCnt = 0;

        // 플레이하고자 하는 사람들의 닉네임을 구하기 위해 반복문 사용
        for (int i = 0; i < n; i++) {
            String nickname = br.readLine();

            // set에 포함되어 있지 않으면 set에 추가 후 플레이어 수 증가
            if (!playedNickname.contains(nickname)) {
                playedNickname.add(nickname);
                playerCnt++;
            }

            // 플레이어 수가 게임 최대 플레이어 수와 동일하면 플레이어 수 초기화 및 게임 진행 횟수 증가
            if(playerCnt == maxPlayer) {
                playerCnt = 1;
                playGameCnt++;
            }
        }
        br.close();
        System.out.println(playGameCnt);
    }
}
