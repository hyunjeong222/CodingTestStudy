import java.util.*;
import java.io.*;

public class Main {
    static final int N = 4;
    static class Shark {
        int x, y, dir, eatSum;

        Shark (int x, int y, int dir, int eatSum) {
            this.x = x; this.y = y;
            this.dir = dir; this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, id, dir;
        boolean isAlive;

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x; this.y = y;
            this.id = id; this.dir = dir; this.isAlive = isAlive;
        }
    }

    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[N][N];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int id = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishes.add(new Fish(i, j, id, dir, true));
                arr[i][j] = id;
            }
        }

        // 물고기는 작은 순서부터 이동
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.id - o2.id;
            }
        });

        // 상어는 (0, 0) 물고기를 먹고 시작하며 위치는 -1 로 표현
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(f.x, f.y, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = -1;

        dfs(arr, shark, fishes);
        
        System.out.println(maxSum);
    }

    static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 순서대로 이동
        for (Fish f : fishes) {
            moveFish(f, arr, fishes);
        }

        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;

            if (rangeCheck(nx, ny) && arr[nx][ny] > 0) {
                // 백트래킹 대신 배열, 리스트 복사
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                arrCopies[shark.x][shark.y] = 0; // 먹힌 물고기 표시
                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1; // 상어 표시

                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }

    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (!fish.isAlive) return;

        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (rangeCheck(nx, ny) && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;

                if (arr[nx][ny] == 0) { // 물고기 혼자 이동
                    fish.x = nx;
                    fish.y = ny;
                } else { // 물고기 서로 이동
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    // fishes 리스트에서 위치를 교환해서 저장
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }

    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        for (Fish f : fishes) {
            temp.add(new Fish(f.x, f.y, f.id, f.dir, f.isAlive));
        }

        return temp;
    }

    private static boolean rangeCheck(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}