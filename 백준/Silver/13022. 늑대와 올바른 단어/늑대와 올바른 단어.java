import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.length() < 4) { // wolf 만들 수 없는 경우
            System.out.println(0);
            return;
        }

        int wCnt = 0, oCnt = 0, lCnt = 0, fCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) { // 첫번째 단어는 무조건 w
                if (str.charAt(i) != 'w') {
                    System.out.println(0);
                    return;
                }
                wCnt++;
                if (!(str.charAt(i+1) != 'w' || str.charAt(i+1) != 'o')) {
                    System.out.println(0);
                    return;
                }
            } else if (i == str.length()-1) { // 마지막 단어는 무조건 f
                // w는 위에서 조건 체크 완료
                // o와 l의 갯수가 같아야 함
                if (str.charAt(i) != 'f' || oCnt != lCnt) {
                    System.out.println(0);
                    return;
                }
                fCnt++;
                // 그 후 f의 갯수가 같은지 체크
                if (lCnt != fCnt) {
                    System.out.println(0);
                    return;
                }
            } else {
                if (str.charAt(i) == 'w') {
                    if (str.charAt(i-1) == 'f') {
                        wCnt = 0; oCnt = 0; lCnt = 0; fCnt = 0;
                    }
                    wCnt++;
                    if (!(str.charAt(i+1) == 'w' || str.charAt(i+1) == 'o') || lCnt != fCnt) {
                        System.out.println(0);
                        return;
                    }
                } else if (str.charAt(i) == 'o') {
                    oCnt++;
                    if (!(str.charAt(i+1) == 'o' || str.charAt(i+1) == 'l')) {
                        System.out.println(0);
                        return;
                    }
                } else if (str.charAt(i) == 'l') {
                    lCnt++;
                    if (!(str.charAt(i+1) == 'l' || str.charAt(i+1) == 'f') || wCnt != oCnt) {
                        System.out.println(0);
                        return;
                    }
                } else { // f
                    fCnt++;
                    if (!(str.charAt(i+1) == 'f' || str.charAt(i+1) == 'w') || oCnt != lCnt) {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        // 위에 모든 조건을 통과
        System.out.println(1);

        br.close();
    }
}