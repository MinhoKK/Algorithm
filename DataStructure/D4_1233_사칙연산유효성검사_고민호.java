package DataStructure;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D4_1233_사칙연산유효성검사_고민호 {
	/**
	 * @author 고민호
	 * Swea_1233_사칙연산유효성검사
	 * 난이도 D4
	 * 
	 * 풀이방법
	 * 1. 자식노드가 존재한다면 상위 노드는 반드시 연산자 (숫자는 자식 노드를 가질 수 없다)
	 */
	static int N;
	static int result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			N = parseInt(br.readLine());
			result = 1;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int index = parseInt(st.nextToken());
				char operator = st.nextToken().charAt(0);
				
				// 번호, 연산자 또는 숫자를 입력받은뒤 토큰이 남아있다면 이는 자식노드
				// 자식노드가 존재하는 경우
				if(st.hasMoreTokens()) {
					// 상위 노드가 숫자인 경우 무효
					if(operator >= '0' && operator <='9') {
						result = 0;
						continue;
					}
				// 자식노드가 없는 경우
				} else {
					// 상위 노드가 연산자인 경우 무효
					if(operator < '0' && operator > '9') {
						result = 0;
						continue;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	
	}

	
}
