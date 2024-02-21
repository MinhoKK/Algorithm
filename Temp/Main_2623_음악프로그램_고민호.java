package Temp;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2623_음악프로그램_고민호 {
	/**
	 * @author 고민호
	 * 백준_2623_음악프로그램
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 14,716ms		시간 : 148ms
	 * 
	 * 풀이방법
	 * 1. 위상정렬로 문제 해결
	 * 2. 가수 수만큼의 크기를 가진 1차원 배열을 만들어 각 가수(노드)가 가지는 진입차수를 값으로 가지게 한다.
	 * 3. 진입차수가 0이면 Queue에 담기
	 * 4. Queue에서 poll, 해당 노드와 연결된 간선 제거, 간선 제거로 인해 진입차수가 0이된 노드가 발생하면 Queue에 담기
	 */

	static int N, M;	// 가수 수, PD 수
	static int[] input;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		input = new int[N];
		
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = parseInt(st.nextToken());
			int[] tempArr = new int[num];
			// 담당한 가수 수만큼 가수 번호 받기
			for(int j=0; j<num; j++) {
				tempArr[j] = parseInt(st.nextToken()) - 1;
			}
			
			for(int j=0; j<num-1; j++) {
				list.get(tempArr[j]).add(tempArr[j+1]);	// 노드(가수) 단방향 연결
			}
			for(int j=1; j<num; j++) {
				input[tempArr[j]]++;	// 진입차수 증가

			}
		}
		
		// 진입차수가 0인 노드(가수) Queue에 추가
		for(int i=0; i<N; i++) {
			if(input[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int out = queue.poll();
			sb.append(out+1).append("\n");
			
			for(int index : list.get(out)) {
				input[index]--;	// 연결된 간선 자르기 -> 목적 노드의 진입차수 -1
				if(input[index] == 0) {	// 진입차수가 0인 노드가 생기면 Queue에 추가
					queue.add(index);
				}
			}
		}
		
		// 순서를 정하지 못하는 경우
		// Queue가 비어 while문을 빠져나왔으나 진입차수가 0이 아닌 노드가 남아있는 경우
		for(int index : input) {
			if(index > 0) {
				sb.setLength(0);
				sb.append(0);
				break;
			}
		}
		
		System.out.println(sb);
		
		
	}

}
