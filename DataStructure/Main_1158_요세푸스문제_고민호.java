package DataStructure;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1158_요세푸스문제_고민호 {
	/**
	 * @author 고민호
	 * 백준_1158_요세푸스문제
	 * 난이도 S4
	 * 결과 : 통과		메모리 : 295,204KB		시간 : 600ms
	 * 
	 * 풀이방법
	 * 1. Queue 자료구조를 사용하기
	 * 2. 사람수를 Count해가며 3번째일 경우 제거만, 아닐경우 뒤로 옮기기 -> add(0), poll()
	 */
	static int N, K;	// 사람 수, 제거 순서
	static int count;	// 사람 번호 카운트
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> queue = new LinkedList<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		K = parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		sb.append("<");
		// Queue안에 값이 존재한다면 반복
		while(!queue.isEmpty()) {
			// K번째 사람을 제거하기 위해
			if(count == K-1) {
				if(queue.size() > 1) {
					sb.append(queue.poll()).append(", ");
					count = 0;	// 다시 K번째 사람을 제거하기 위해 초기화
					continue;
				}
				// 마지막은 , 출력 X
				else {
					sb.append(queue.poll());
					count = 0;
					continue;
				}
			}
			// K번째가 아니라면 맨앞을 맨뒤로 옮기기
			queue.add(queue.poll());
			// 사람 카운트 수 +1
			count++;
		}
	
		sb.append(">");
		System.out.println(sb);
	}

}
