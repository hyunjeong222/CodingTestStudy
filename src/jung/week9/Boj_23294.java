package src.jung.week9;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 웹 브라우저 1(GOL)
 * 시간 : 160ms
 * 메모리 : 19648KB
 * 링크 : https://www.acmicpc.net/problem/23294
 */
public class Boj_23294 {
    static int n, q, c;
    static int[] cache;
    static int cacheSize, cur;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 웹페이지의 종류의 수
        q = Integer.parseInt(st.nextToken()); // 사용자가 수행하는 작업의 개수
        c = Integer.parseInt(st.nextToken()); // 최대 캐시 용량

        cacheSize = 0; // 캐시 용량
        cur = 0; // 현재 페이지

        cache = new int[n+1]; // 각 페이지의 캐시 크기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cache[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> back = new LinkedList<>(); // 뒤로 가기 공간
        Deque<Integer> front = new LinkedList<>(); // 앞으로 가기 공간
        Deque<Integer> tmp; // 압축된 상태를 저장할 공간

        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s) {
                case "B" : // 뒤로 가기
                    if (!back.isEmpty()) { // 1개 이상의 페이지가 저장되어 있다면
                        front.offerFirst(cur); // 현재 페이지 앞으로 가기 공간에 저장
                        cur = back.pollFirst(); // 뒤로 가기 공간에서 가장 최근 페이지 접속 후 그 페이지 뒤로 가기 공간에서 제거
                    }
                    break;
                case "F" : // 앞으로 가기
                    if (!front.isEmpty()) { // 1개 이상의 페이지가 저장되어 있다면
                        back.offerFirst(cur); // 현재 페이지 뒤로 가기 공간에 저장
                        cur = front.pollFirst(); // 앞으로 가기 공간에서 가장 최근 페이지 접속 후 그 페이지 앞으로 가기 공간에서 제거
                    }
                    break;
                case "A" : // 페이지 접속
                    int page = Integer.parseInt(st.nextToken()); // 페이지 번호
                    while (!front.isEmpty()) { // 앞으로 가기 공간에 저장된 페이지 모두 삭제, 페이지들이 차지하고 있던 크기만큼 현재 사용 캐시에서 줄어듬
                        cacheSize -= cache[front.pollFirst()];
                    }

                    if (cur != 0) back.offerFirst(cur); // 처음 접속한 페이지가 아니라면 현재 페이지 뒤로 가기 공간에 추가
                    cur = page; // 현재 페이지 갱신
                    cacheSize += cache[cur]; // 접속한 페이지의 용량만큼 현재 사용 캐시 용량 추가

                    while (cacheSize > c) { // 최대 캐시 용량을 초과할 경우, 여러번 수행 가능
                        cacheSize -= cache[back.pollLast()]; // 뒤로 가기 공간에서 가장 오래된 페이지 하나 삭제, 현재 사용 캐시 용량에서 그 페이지 캐시 만큼 줄어듬
                    }
                    break;
                case "C" : // 압축
                    if (back.isEmpty()) continue;
                    tmp = new LinkedList<>();
                    int prev = back.pollFirst();
                    tmp.offer(prev);

                    for (int next : back) {
                        if (next != prev) { // 2개 이상 연속해서 페이지가 등장하지 않는다면
                            tmp.add(next); // 다음 페이지 담아주고
                            prev = next; // 이전 페이지 갱신
                        } else { // 연속해서 등장한다면
                            cacheSize -= cache[next]; // 삭제된 페이지가 차지하고 있던 용량만큼 현제 사용 캐시에서 줄여주기
                        }
                    }
                    back = tmp;
                    break;
            }
        }

        // 출력
        sb.append(cur).append("\n");
        getPage(sb, back);
        getPage(sb, front);

        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void getPage(StringBuilder sb, Deque<Integer> q) {
        if (q.isEmpty()) sb.append(-1);
        else {
            while (!q.isEmpty()) sb.append(q.pollFirst()).append(" ");
        }
        sb.append("\n");
    }
}