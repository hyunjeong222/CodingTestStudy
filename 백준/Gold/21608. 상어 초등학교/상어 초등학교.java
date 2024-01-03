import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[] students;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Student implements Comparable<Student> {
        int x; int y;
        int cnt; int empty;
        public Student (int x, int y, int cnt, int empty) {
            this.x = x; this.y = y;
            this.cnt = cnt; this.empty = empty;
        }
        @Override
        public int compareTo(Student o) {
            if (this.cnt == o.cnt) {
                if (this.empty == o.empty) {
                    if (this.x == o.x) {
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return o.empty - this.empty;
            }
            return o.cnt - this.cnt;
        }
    }
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        students = new int[n*n+1];
        list = new ArrayList<>();
        for (int i = 0; i <= n*n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= n*n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            students[i] = student;
            
            for (int j = 0; j < 4; j++) {
                int lNum = Integer.parseInt(st.nextToken());
                list.get(student).add(lNum);
            }
        }
        
        for (int i = 1; i <= n*n; i++) {
            sit(students[i]);
        }

        getScore();

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void sit(int num) {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) continue;
                pq.offer(getStudent(i, j, num));
            }
        }
        int x = pq.peek().x;
        int y = pq.peek().y;
        map[x][y] = num;
    }

    private static Student getStudent(int x, int y, int num) {
        int cnt = 0;
        int emptyCnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (list.get(num).contains(map[nx][ny])) cnt++;
                if (map[nx][ny] == 0) emptyCnt++;
            }
        }

        return new Student(x, y, cnt, emptyCnt);
    }

    private static void getScore() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + x;
                    int ny = dy[i] + y;

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (list.get(map[x][y]).contains(map[nx][ny])) cnt++;
                    }
                }
                if (cnt == 1) ans += 1;
                else if (cnt == 2) ans += 10;
                else if (cnt == 3) ans += 100;
                else if (cnt == 4) ans += 1000;
            }
        }
    }
}