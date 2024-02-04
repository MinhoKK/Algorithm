package DataStructure;

import java.io.*;
import java.util.*;

public class Main_2493_탑_고민호 {
	/**
	 * @author 고민호
	 * 다음에 다시 풀어보기
	 * 백준_2493_탑
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 86,580KB		시간 : 824ms
	 * 
	 * 풀이방법
	 * 1. 배열을 통한 완전탐색으로 문제를 해결하려 했으나 N이 최대 500,000으로 최악의 경우 O(N^2)로 제한시간 1.5초를 벗어난다고 생각
	 * 2. Stack 자료구조를 사용하여 문제해결
	 * 3. Stack에 push하기 전 Stack의 Top과 비교하기
	 */

	// 탑 클래스
	static class Top{
		int num;	// 탑 번호
		int height;	// 탑 높이
		
		// 탑 클래스 생성자
		public Top(int num, int height) {
			this.num = num;
			this.height = height;
		}
		
	}
	
	static int N;	// 탑의 개수
	static Stack<Top> stack = new Stack<>();	// 탑을 저장하는 Stack
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		// i=1 부터 시작하여 N까지 반복하여 탑 정보 받기 -> 탑의 번호와 매칭하기 위해
		for(int i=1; i<=N; i++) {
			// 탑 객체 생성
			Top top = new Top(i, Integer.parseInt(st.nextToken()));
			
			// Stack이 비어있는 경우 -> 레이저 신호를 수신할 탑이 없는 경우로 0 출력 및 Push
			if(stack.size() == 0) {
				sb.append(0).append(" ");
				stack.push(top);
				continue;
			}
			
			// Stack에 탑이 존재하는 경우
			// 새로운 탑이 stack에 push 될때까지 반복
			while(true) {
				// 밑에 구문에서 stack.pop()을 하여 스택이 빈 경우 -> 레이저 신호를 수신할 탑이 없는 경우로 0 출력 및 Push
				if(stack.size() == 0) {
					sb.append(0).append(" ");
					stack.push(top);
					break;
				}
				
				Top nowTop = stack.peek();	// 스택의 Top에 위치한 탑객체
				
				// Top에 위치한 탑객체의 높이 >= 새로운 탑객체의 높이
				// 레이저를 수신할 수 있다는 의미
				if(nowTop.height >= top.height) {
					sb.append(nowTop.num).append(" ");	// Top에 위치한 탑객체(레이저를 수신하는 탑)의 번호 출력
					stack.push(top);	// Stack에 새로운 탑객체 추가
					break;
				// Top에 위치한 탑객체의 높이 < 새로운 탑객체의 높이
				// 레이저를 수신할 수 없다는 의미
				} else {
					stack.pop();	// 다음 탑객체를 Top으로 올리기 위해 Pop
				}
			}
			
		}
		System.out.println(sb);
	}
}
