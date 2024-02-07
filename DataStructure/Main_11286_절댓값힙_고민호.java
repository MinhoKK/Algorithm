package DataStructure;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_11286_절댓값힙_고민호 {
	/**
	 * @author 고민호
	 * 백준_11286_절댓값힙
	 * 난이도 S1
	 * 결과 : 통과		메모리 : 26,540KB		시간 : 328ms
	 * 
	 * 풀이방법
	 * 1. 작은 값대로 정렬, 가장 작은 값 출력 및 제거 -> 우선순위 큐 자료구조 사용하기
	 * 2. 절대값이 가장 작은 값 출력 -> 우선순위 큐 우선순위 compare 메서드 재정의하기
	 */

	static int N;
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			int value = Math.abs(o1) - Math.abs(o2);	// 절대값 오름차순
			if(value == 0) return o1 - o2;	// 절대값이 동일하다면 기본값 오름차순
			
			return value;
		}
	});
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = parseInt(br.readLine());
			
			// 0이 입력된 경우
			if(num == 0) {
				// 큐가 빈 경우 0 출력
				if(queue.isEmpty()) {
					sb.append("0\n");
					continue;
				} else {
					// 가장 작은 값 출력 -> 우선순위 큐로 조건에 맞춰 자동 정렬
					sb.append(queue.poll()).append("\n");
					continue;
				}
			}
			
			// 0 이외 숫자 입력된 경우 우선순위 큐에 추가
			queue.offer(num);
		}
		
		System.out.println(sb);
	}

}
