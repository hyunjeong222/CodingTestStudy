import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 방의 개수
        long hAtk = Long.parseLong(st.nextToken()); // 용사의 초기 공격력

        long hCurHp = 0; // 현재 용사의 생명력
        long hMaxHp = 0; // 용사의 최대 생명력

        // t가 1인 경우 공격력이 a, 생명력이 h인 몬스터 존재
        // t가 2인 경우 용사의 공격력 hAtk를 a만큼 증가, 현재 생명력 hCurHp를 h만큼 회복
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            if (type == 1) { // 싸울때
                // 몬스터 무찌름
                hCurHp += attack * ((hp / hAtk) - (hp % hAtk != 0 ? 0 : 1));
                hMaxHp = Math.max(hMaxHp, hCurHp);
            } else { // type = 2
                hAtk += attack; // 공격력 증가
                hCurHp = Math.max(hCurHp - hp, 0);
            }
        }
        hMaxHp++;
        bw.append(hMaxHp + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}