import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Info implements Comparable<Info> {
        int price; int quality;
        public Info (int price, int quality) {
            this.price = price; this.quality = quality;
        }
        @Override
        public int compareTo (Info o) {
            // 가격 낮은순
            return this.price - o.price;
        }
    }

    static public class Product implements Comparable<Product> {
        String type; int price; int quality;
        public Product (String type, int price, int quality) {
            this.type = type; this.price = price; this.quality = quality;
        }
        @Override
        public int compareTo (Product o) {
            // 능력치 낮은순
            return this.quality - o.quality;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n, b;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 부품의 개수
            b = Integer.parseInt(st.nextToken()); // 예산

            HashMap<String, PriorityQueue<Info>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                String name = st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                int quality = Integer.parseInt(st.nextToken());

                if (map.get(type) == null) {
                    map.put(type, new PriorityQueue<>());
                }
                map.get(type).offer(new Info(price, quality));
            }

            // 싼 부품들로 컴퓨터 조립
            PriorityQueue<Product> selected = new PriorityQueue<>();
            int price = 0;
            for (String type : map.keySet()) {
                Info cheap = map.get(type).poll();
                price += cheap.price;
                selected.offer(new Product(type, cheap.price, cheap.quality));
            }

            // System.out.println(price);

            int ans = 0;
            // 능력치 낮은거부터 꺼내서 컴퓨터 성능 올리기
            while (true) {
                Product now = selected.poll();

                Info next = null;
                boolean flag = false;
                while (true) { // 능력 더 좋은 부품 탐색
                    if (map.get(now.type).isEmpty()) break;                   

                    next = map.get(now.type).poll();
                    if (next.quality <= now.quality) continue; // 현재 퀄리티와 같거나 현재 퀄리티가 더 높다면
                    if (b < price - now.price + next.price) break; // 예산 초과

                    // 현재 퀄리티보다 높고 예산 안에 들어간다면
                    flag = true;
                    break;
                }

                if (flag) {
                    price = price - now.price + next.price;
                    selected.offer(new Product(now.type, next.price, next.quality));
                } else {
                    ans = now.quality;
                    break;
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}