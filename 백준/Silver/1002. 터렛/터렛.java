import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());//테스트 개수
		int x1,y1,r1,x2,y2,r2;
		double d;//(x1,y1)와 (x2,y2)사이의 거리
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			x1=Integer.parseInt(st.nextToken());
			y1=Integer.parseInt(st.nextToken());
			r1=Integer.parseInt(st.nextToken());
			x2=Integer.parseInt(st.nextToken());
			y2=Integer.parseInt(st.nextToken());
			r2=Integer.parseInt(st.nextToken());
			
			d=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
			
			if(x1==x2 && y1==y2) {//중심점이 같을 경우
				if(r1==r2)//반지름이 같으면 무한대(원이 같음)
					sb.append(-1+"\n");
				else//반지름이 다르면 0개(원이 겹치지 않음)
					sb.append(0+"\n");
			}
			else {//원이 중심점이 다를 경우
				if(r1+r2 < d || (r1-r2)*(r1-r2)>d*d)//원이 서로 바깥 or 내부에서 떨어져있을 경우
					sb.append(0+"\n");//0개
				else if(r1+r2==d || (r1-r2)*(r1-r2)==d*d)//원이 외접하거나 내접할 경우
					sb.append(1+"\n");//1개
				else if((r1-r2)*(r1-r2)<d*d && d*d<(r1+r2)*(r1+r2))//원이 서로 일부가 겹쳐져 있을 경우
					sb.append(2+"\n");//2개
			}
		}
		System.out.print(sb);
		br.close();
	}
}