package src.jung.Baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 문제 이름(난이도) : 비슷한 단어(SIL)
 * 시간 : 124ms
 * 메모리 : 13072KB
 * 링크 : https://www.acmicpc.net/problem/1411
 */
public class Boj_1411 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 단어의 개수
        String[] str = new String[n]; // 단어 저장
        for (int i = 0; i < n; i++) {
            str[i] = br.readLine();
        }

        int cnt = 0; // 쌍의 개수
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                boolean flag = true;
                HashMap<Character, Character> map = new HashMap<>();

                for (int k = 0; k < str[i].length(); k++) {
                    char origin = str[i].charAt(k);
                    char compare = str[j].charAt(k);

                    if (map.containsKey(origin)) {
                        if (map.get(origin) != compare) {
                            flag = false;
                            break;
                        }
                    } else {
                        Iterator<Character> keys = map.keySet().iterator();
                        while (keys.hasNext()) {
                            char key = keys.next();
                            if (map.get(key) == compare) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) map.put(origin, compare);
                    }
                }

                if (flag) cnt++;
            }
        }
        bw.append(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
