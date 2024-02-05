package DataStructure;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_2493_탑_고민호 {
	/**
	 * @author 고민호
	 * 백준_2493_탑
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 84,560KB		시간 : 868ms
	 * 
	 * 풀이방식
	 * 1. Stack 자료구조 사용하기
	 * 2. 첫번째 탑의 레이저 수신 가능하 탑은 존재 X
	 * 3. 두번째 탑부터 Stack의 top에 있는 탑의 높이와 비교하며 수신가능한 탑 찾기 
	 */
	static class Top{
		int num;
		int height;
		
		public Top(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
	
	static int N;	// 탑의 개수
	static int num = 1;	// 탑의 번호
	static Stack<Top> stack = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			// 탑 객체는 탑번호, 탑높이를 가진다
			// 탑번호는 객체가 늘때마다 +1
			Top top = new Top(num++, parseInt(st.nextToken()));
			
			// 맨처음 탑의 레이저를 수신가능한 탑은 존재X -> 반드시 0 출력
			if(stack.isEmpty()) {
				sb.append(0).append(" ");
				stack.push(top);
				continue;
			}
			
			// 수신가능한 탑 찾기가 종료되는 경우의 수 2가지
			// 1. 자신의 높이와 같거나 높은 높이를 가진 탑을 발견한 경우
			// 2. 자신의 앞에 수신가능한 탑이 없는 경우
			while(true) {
				// 1. 수신가능한 탑을 발견한 경우
				if(stack.peek().height >= top.height) {
					sb.append(stack.peek().num).append(" ");	// 해당 탑의 번호 출력
					stack.push(top);	// 현재 탑 stack에 추가
					break;
				} else {
					// 바로 앞 탑이 수신이 불가능한 경우
					stack.pop();	// stack에서 제거
				}
				
				// 수신가능한 탑이없어 stack에서 계속 pop을 해오다 비교가능한 탑이 더이상 없는 경우 
				// -> 수신가능한 탑 X -> 0 출력
				if(stack.isEmpty()) {
					sb.append(0).append(" ");
					stack.push(top);	// 현재 탑 stack에 추가
					break;
				}
				
			}
		}
		System.out.println(sb);
	}

}
