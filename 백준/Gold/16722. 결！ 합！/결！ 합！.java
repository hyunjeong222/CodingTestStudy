import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Picture {
        String shape, color, back;
        Picture (String shape, String color, String back) {
            this.shape = shape; this.color = color; this.back = back;
        }
    }
    static List<Picture> pictures = new ArrayList<>();
    static Set<String> hapSet = new HashSet<>();
    static int score = 0;
    static boolean usedG = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
             st = new StringTokenizer(br.readLine());
             // 모양, 색, 배경색
             pictures.add(new Picture(st.nextToken(), st.nextToken(), st.nextToken()));
        }

        // 모든 합 미리 계산
        buildAllHaps();

        int n = Integer.parseInt(br.readLine()); // 기록 수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            Character type = st.nextToken().charAt(0);
            if (type == 'H') {
                int[] comb = new int[3];
                comb[0] = Integer.parseInt(st.nextToken())-1;
                comb[1] = Integer.parseInt(st.nextToken())-1;
                comb[2] = Integer.parseInt(st.nextToken())-1;
                Arrays.sort(comb);

                String key = makeKey(comb[0], comb[1], comb[2]);

                if (hapSet.contains(key)) {
                    hapSet.remove(key);
                    score++;
                } else score--;
            } else { // G
                // 외치지 않은 합이 없고, 결을 통해 3점을 얻은 적 없을때
                if (!usedG && hapSet.isEmpty()) {
                    usedG = true;
                    score += 3;
                } else score--;
            }
        }

        System.out.println(score);

        br.close();
    }

    private static void buildAllHaps() {
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                for (int k = j+1; k < 9; k++) {
                    Picture p1 = pictures.get(i);
                    Picture p2 = pictures.get(j);
                    Picture p3 = pictures.get(k);

                    boolean flag = true;

                    // 모두 같거나 다르지도 않음
                    if (!allSame(p1.shape, p2.shape, p3.shape) && !allDifferent(p1.shape, p2.shape, p3.shape)) {
                        flag = false;
                    }

                    if (!allSame(p1.color, p2.color, p3.color) && !allDifferent(p1.color, p2.color, p3.color)) {
                        flag = false;
                    }

                    if (!allSame(p1.back, p2.back, p3.back) && !allDifferent(p1.back, p2.back, p3.back)) {
                        flag = false;
                    }

                    if (flag) {
                        hapSet.add(makeKey(i, j, k));
                    }
                }
            }
        }
    }

    private static boolean allSame(String x, String y, String z) {
        return x.equals(y) && y.equals(z);
    }

    private static boolean allDifferent(String x, String y, String z) {
        return !x.equals(y) && !y.equals(z) && !z.equals(x);
    }

    private static String makeKey(int a, int b, int c) {
        return a + "-" + b + "-" + c; // 오름차순
    }
}