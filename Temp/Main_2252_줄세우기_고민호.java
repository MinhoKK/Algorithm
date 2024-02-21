package Temp;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main_2252_줄세우기_고민호 {
	/**
	 * @author 고민호
	 * 백준_2252_줄세우기
	 * 난이도 G3
	 * 결과 : 통과		메모리 : 48,768KB		시간 : 484ms
	 * 
	 * 풀이방법
	 * 1. 위상정렬로 문제 해결
	 * -> 문제에서 위상정렬로 풀라고 주는 힌트(?) = 순서를 정하는데 답이 여러개인 경우
	 * 2. 학생(노드)수와 같은 크기를 가지며, 진입차수 값을 가지는 1차원 배열 생성
	 * 3. 학생 키를 비교한 결과가 주어질때 진입차수 값 더하기
	 * 
	 * 위상정렬
	 * 1. Queue에 진입차수가 0인 노드 담기
	 * 2. Queue에서 poll하며 연결된(나아가는) 간선 지우기
	 * 3. 진입차수 확인, 지워진 간선으로 인해 진입차수가 0인된 노드가 존재한다면 Queue에 담기
	 * 4. Queue가 빌때까지 2, 3반복
	 */

	static int N, M;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> queue = new LinkedList<>();
	static int[] input;
	
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
			int a = parseInt(st.nextToken()) - 1;
			int b = parseInt(st.nextToken()) - 1;
			list.get(a).add(b);
			input[b]++;	// 진입차수 +1
		}
		
		for(int i=0; i<N; i++) {
			if(input[i] == 0) {	// 진입차수가 0인 노드
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int out = queue.poll();	// Queue에서 제거
			sb.append(out+1).append(" ");
			
			for(int index : list.get(out)) {	// 지워진 노드와 연결된 간선 지우기 -> 실제 list의 값을 지우기보단, 연결된 노드의 진입차수 -1
				input[index]--;
				if(input[index] == 0) {	// 진입차수가 0이된다면 Queue에 추가
					queue.add(index);
				}
			}
		}
		
		System.out.println(sb);
	
	}

}
