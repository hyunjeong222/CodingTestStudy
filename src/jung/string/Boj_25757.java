package jung.string;

import java.io.*;
import java.util.*;

public class Boj_25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사람들이 임스와 같이 플레이하기를 신청한 횟수
        String g = st.nextToken(); // 플레이할 게임의 종류

        int player = 0;
        if (g.equals("Y")) player = 1;
        else if (g.equals("F")) player = 2;
        else if (g.equals("O")) player = 3;

        int count = 0; // 플레이 수
        int p = player;
        Set<String> set = new HashSet<>(); // 중복허용 X
        // 한번 게임한 사람하고는 안함
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            set.add(name);
        }
        for (int i = 0; i < set.size(); i++) {
            p--;
            if (p == 0) {
                count++;
                p = player;
            }
        }
        bw.append(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
/*
LinkedList<String> list = new LinkedList<>();
for (int i = 0; i < n; i++) {
    String name = br.readLine();
    if (list.contains(name)) continue;
    else {
        list.add(name);
        p--;
        if (p == 0) {
            count++;
            p = player;
        }
    }
}
*/