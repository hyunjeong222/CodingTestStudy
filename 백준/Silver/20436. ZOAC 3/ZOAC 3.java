import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] left = {{'q', 'w', 'e', 'r', 't'},
                            {'a', 's', 'd', 'f', 'g'},
                            {'z', 'x', 'c', 'v'}};
    static char[][] right = {{' ', 'y', 'u', 'i', 'o', 'p'},
                             {' ', 'h', 'j', 'k', 'l'},
                             {'b', 'n', 'm'}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        HashMap<Character, int[]> map = new HashMap<>(); // 키보드 좌표 저장
        // 왼손
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < left[i].length; j++) {
                map.put(left[i][j], new int[]{i, j});
            }
        }
        // 오른손
        for (int i = 0; i < right.length; i++) {
            for (int j = 0; j < right[i].length; j++) {
                map.put(right[i][j], new int[]{i, j});
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 왼손, 오른손 처음 위치
        char sl = st.nextToken().charAt(0);
        char sr = st.nextToken().charAt(0);

        char[] str = br.readLine().toCharArray();
        // 왼손, 오른손 처음 위치 좌표
        int[] currentLeft = map.get(sl);
        int[] currentRight = map.get(sr);

        int time = 0; // 출력하는 데 걸리는 최소 시간

        for (int i = 0; i < str.length; i++) {
            int[] strLocation = map.get(str[i]);

            if (checkKey(str[i])) { // 한글 자음이면 - 왼손
                time += Math.abs(currentLeft[0]-strLocation[0])+Math.abs(currentLeft[1]-strLocation[1]);
                currentLeft = strLocation; // 현재 손 위치 변경
            } else { // 모음이면 - 오른손
                time += Math.abs(currentRight[0]-strLocation[0])+Math.abs(currentRight[1]-strLocation[1]);
                currentRight = strLocation; // 현재 손 위치 변경
            }

            time += 1;
        }


        System.out.println(time);

        br.close();
    }

    private static boolean checkKey(char c) {
        return "qwertasdfgzxcv".indexOf(c) != -1;
    }
}