package src.jung.week8;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 드래곤 앤 던전(GOL4)
 * 시간 : 364ms
 * 메모리 : 52320KB
 * 링크 : https://www.acmicpc.net/problem/16434
 */
public class Boj_16434 {
    // 모든 방을 클리어했을때 용사가 받을 수 있는 최대 데미지 -> 필요한 체력
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 방의 개수
        long hAtk = Long.parseLong(st.nextToken()); // 용사의 공격력

        long hCurHp = 0; // 현재 용사의 생명력 -> 현재 용사의 데미지
        long hMaxHp = 0; // 용사의 최대 생명력 -> 용사가 받은 최대 데미지

        // t가 1인 경우 공격력이 a, 생명력이 h인 몬스터 존재
        // t가 2인 경우 용사의 공격력 hAtk를 a만큼 증가, 현재 생명력 hCurHp를 h만큼 회복
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            if (type == 1) { // 몬스터가 있는 방
                // 몬스터를 t번의 공격으로 잡는다면
                // 몬스터는 t-1번의 공격만 한다
                // 용사가 받은 데미지 = 몬스터의 공격력 * 몬스터를 잡기위해 공격한 횟수
                hCurHp += attack * ((hp / hAtk) - (hp % hAtk != 0 ? 0 : 1));
                hMaxHp = Math.max(hMaxHp, hCurHp);
            } else { // type = 2, 포션 먹는 방
                hAtk += attack; // 공격력을 attack만큼 증가
                hCurHp = Math.max(hCurHp - hp, 0); // 받은 데미지 - 포션 = 회복하고 나서의 데미지, 0 이하만 안되게
            }
        }
        hMaxHp++;
        bw.append(hMaxHp + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
