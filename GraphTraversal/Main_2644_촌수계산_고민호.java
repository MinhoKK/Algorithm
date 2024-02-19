package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2644_촌수계산_고민호 {
	/**
	 * @author 고민호
	 * 백준_2644_촌수계산
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 14,196KB		시간 : 128ms
	 * 
	 * 풀이방법
	 * 1. 문제를 보고 양뱡향 트리(촌수 구조)를 BFS로 탐색하자고 생각했습니다.
	 * 2. Queue에 새로운 Node class가 추가될때 이전 Node의 count(시작으로부터 현재까지의 촌수)dp +1을 했습니다.
	 */
	
	static class Node{
		int value;	// 번호
		int count;	// 촌수
		
		public Node(int value, int count) {
			this.value = value;
			this.count = count;
		}
	}

	static int N;	// 전체 사람 수
	static int A, B;	// 구해야하는 촌수
	static int M;	// 관계 수
	static int[] visit;	// 방문 여부 배열
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = parseInt(br.readLine());
		visit = new int[N];
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		A = parseInt(st.nextToken()) - 1;
		B = parseInt(st.nextToken()) - 1;
		
		M = parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = parseInt(st.nextToken()) - 1;
			int y = parseInt(st.nextToken()) - 1;
			// 양방향 트리
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		System.out.println(bfs(A));	// A에서 BFS시작
	}

	
	static int bfs(int start) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));	// 시작지점은 촌수는 0
		visit[start] = 1;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			if(out.value == B) {	// 현재 노드가 찾는 노드인 경우
				return out.count;
			}
			for(int linkedNode : list.get(out.value)) {	// 현재 노드와 연결되 노드 탐색
				if(visit[linkedNode] == 0) {	// 방문하지 않은 노드인 경우
					queue.add(new Node(linkedNode, out.count+1));
					visit[linkedNode] = 1;
				}
			}
		}
		
		// 찾는 노드를 찾지 못한 경우(친척 관계 X)
		return -1;
	}
}
