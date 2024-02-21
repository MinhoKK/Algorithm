package Temp;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D4_3289_서로소집합_고민호 {
	/**
	 * @author 고민호
	 * SWEA_3289_서로소집합
	 * 난이도 D4
	 * 결과 : 통과		메모리 : 106,672KB		시간 : 662ms
	 * 
	 * 풀이방법
	 * 1. 서로소 집합 구현!!
	 */

	static int T, N, M;
	static int[] parent;	// 부모 노드를 저장하는 배열
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = parseInt(st.nextToken());
			M = parseInt(st.nextToken());
			
			parent = new int[N];
			for(int i=0; i<N; i++) {
				parent[i] = i;	// 처음에는 자기 자신을 가르키도록 초기화
			}
			
			sb.append("#").append(t).append(" ");
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int order = parseInt(st.nextToken());
				int a = parseInt(st.nextToken()) - 1;	// 주어지는 첫번째 노드
				int b = parseInt(st.nextToken()) - 1;	// 주어지는 두번째 노드
				
				if(order == 0) {
					union(a, b);	// b의 부모노드를 a의 부모노드에 붙이기
				} else if(order == 1) {
					if(find(a) == find(b)) {	// 같은 부모를 가진다면 동일한 집합에 속한다는 의미
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	
	static int find(int index) {
		if(parent[index] == index)
			return index;
		else {
			parent[index] = find(parent[index]);
			return parent[index];
		}
	}
	
	static void union(int a, int b) {
		int p1 = find(a);	// a노드의 부모노드 찾기
		int p2 = find(b);	// b노드의 부모노드 찾기
		
		parent[p2] = p1;	// b노드의 부모노드를 a노드의 부모노드에 붙이기
	}
}
