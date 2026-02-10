import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class Problem {
        int num; int level; // 문제 번호, 난이도
        public Problem (int num, int level) {
            this.num = num; this.level = level;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 문제의 개수

        Map<Integer, Problem> map = new HashMap<>(); // 문제 번호, 난이도
        PriorityQueue<Problem > easyProblems = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.level != o2.level) return o1.level-o2.level;
                return o1.num-o2.num;
            }
        });
        PriorityQueue<Problem > hardProblems = new PriorityQueue<>(new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.level != o2.level) return o2.level-o1.level;
                return o2.num-o1.num;
            }
        });

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 문제 번호
            int l = Integer.parseInt(st.nextToken()); // 문제 난이도

            Problem problem = new Problem(p, l);
            map.put(p, problem);
            easyProblems.offer(problem);
            hardProblems.offer(problem);
        }

        int m = Integer.parseInt(br.readLine()); // 명령문 개수
        while (m-->0) {
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            if (cmd.equals("recommend")) {
                int i = Integer.parseInt(st.nextToken());

                if (i == 1) {
                    sb.append(hardProblems.peek().num).append("\n");
                } else if (i == -1) {
                    sb.append(easyProblems.peek().num).append("\n");
                }
            } else if (cmd.equals("add")) {
                int p = Integer.parseInt(st.nextToken()); // 문제 번호
                int l = Integer.parseInt(st.nextToken()); // 문제 난이도

                Problem newProblem = new Problem(p, l);
                if (!map.containsKey(p)) { // 추천 문제 리스트에 없던 새로운 문제
                    easyProblems.offer(newProblem);
                    hardProblems.offer(newProblem);
                } else { // 추천 문제 리스트에 있던 문제가 새로운 난이도로 들어옴
                    // 기존 문제 삭제
                    Problem oldProblem = map.get(p);
                    easyProblems.remove(oldProblem);
                    hardProblems.remove(oldProblem);
                    // 새로운 문제 추가
                    easyProblems.offer(newProblem);
                    hardProblems.offer(newProblem);
                }
            } else if (cmd.equals("solved")) {
                int p = Integer.parseInt(st.nextToken()); // 문제 번호

                Problem oldProblem = map.get(p);
                easyProblems.remove(oldProblem);
                hardProblems.remove(oldProblem);
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}