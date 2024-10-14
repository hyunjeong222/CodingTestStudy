import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 0;
        game(0);

        System.out.println(ans);
    }

    private static void game(int cnt) {
        if (cnt == 5) {
            getMax();
            return;
        }

        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i].clone();
        }

        // 위 아래 왼쪽 오른쪽
        for (int i = 0; i < 4; i++) {
            move(i);
            game(cnt+1);
            for (int j = 0; j < n; j++) { // 다른 경우의 수
                map[j] = copy[j].clone();
            }
        }
    }

    private static void move(int dir) {
        switch (dir) {
            // 위
            case 0 :
                for (int i = 0; i < n; i++) {
                    int idx = 0; // 값을 넣을 위치
                    int block = 0; // 최근 블록 값
                    for (int j = 0; j < n; j++) {
                        if (map[j][i] != 0) {
                            if (map[j][i] == block) {
                                map[idx-1][i] = block*2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            // 아래
            case 1 :
                for (int i = 0; i < n; i++) {
                    int idx = n-1;
                    int block = 0;
                    for (int j = n-1; j >= 0; j--) {
                        if (map[j][i] != 0) {
                            if (map[j][i] == block) {
                                map[idx+1][i] = block*2;
                                block = 0;
                                map[j][i] = 0;
                            } else {
                                block = map[j][i];
                                map[j][i] = 0;
                                map[idx][i] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            // 왼쪽
            case 2 :
                for (int i = 0; i < n; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0) {
                            if (map[i][j] == block) {
                                map[i][idx-1] = block*2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            // 오른쪽
            case 3 :
                for (int i = 0; i < n; i++) {
                    int idx = n-1;
                    int block = 0;
                    for (int j = n-1; j >= 0; j--) {
                        if (map[i][j] != 0) {
                            if (map[i][j] == block) {
                                map[i][idx+1] = block*2;
                                block = 0;
                                map[i][j] = 0;
                            } else {
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
    }

    private static void getMax() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, map[i][j]);
            }
        }
    }
}
