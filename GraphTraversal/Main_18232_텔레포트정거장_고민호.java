package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_18232_텔레포트정거장_고민호 {
	/**
	 * @author 고민호
	 * 백준_18232_텔레포트정거장
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 135,204KB	시간 : 880ms
	 * 
	 * 풀이방법
	 * 1. 문제를 보자마자 이전에 푼 숨바꼭질4와 비슷한 유형이라는 생각이 들었습니다.
	 * 2. BFS을 통해 이동가능한 경우 탐색하기!
	 * 3. BFS 실행 중 poll된 Node의 c값이 E인경우 while문 종료 및 해당 Node의 time 출력 
	 */

	static class Node{
		int c;	// 위치값 변수
		int time;	// 해당 노드에 도착하는 시간 변수
		
		public Node(int c, int time) {
			this.c = c;
			this.time = time;
		}
	}
	
	static int N, M, S, E;	// 배열 크기, 텔레포트 수, 주예 위치, 방주 위치
	static int[] map;
	static int[] visit;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N];
		visit = new int[N];
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		S = parseInt(st.nextToken()) - 1;
		E = parseInt(st.nextToken()) - 1;
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = parseInt(st.nextToken()) - 1;
			int b = parseInt(st.nextToken()) - 1;
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		bfs();
	}
	
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(S, 0));	// 시작위치는 주예, 시작기간은 0초
		visit[S] = 1;
		
		while(true) {
			Node out = queue.poll();	// 현재 위치 노드
			if(out.c == E) {	// 현재 위치의 좌표값이 방주인 경우 종료
				System.out.println(out.time);
				return;
			}
			
			// 앞으로 한칸
			if(out.c+1 >= 0 && out.c+1 < N && visit[out.c+1] == 0) {	// 이동하는 곳이 범위 내, 방문하지 않았다면
				queue.add(new Node(out.c+1, out.time+1));
				visit[out.c+1] = 1;
			}
			
			// 앞으로 한칸
			if(out.c-1 >= 0 && out.c-1 < N && visit[out.c-1] == 0) {	// 이동하는 곳이 범위 내, 방문하지 않았다면
				queue.add(new Node(out.c-1, out.time+1));
				visit[out.c-1] = 1;
			}
			
			// 텔레포트을 통한 이동
			for(int nextIndex : list.get(out.c)) {	// 현재 노드가 가지고 있는 텔레포트 전부 탐색
				if(nextIndex >= 0 && nextIndex < N && visit[nextIndex] == 0) {	// 이동하는 곳이 범위 내, 방문하지 않았다면
					queue.add(new Node(nextIndex, out.time+1));
					visit[nextIndex] = 1;
				}
			}
		}
		
	}

}
