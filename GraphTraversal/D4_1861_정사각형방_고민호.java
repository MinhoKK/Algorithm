package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D4_1861_정사각형방_고민호 {
	/**
	 * @author 고민호
	 * Swea_1861_정사각형방
	 * 난이도 D4
	 * 결과 : 통과		메모리 : 91,736KB		실행시간 : 566ms
	 * 
	 * 풀이방법
	 * 1. bfs과 4방탐색을 통해 방문가능한 방을 찾자
	 * 2. bfs 시작좌표마다 방문가능한 최대값을 확인하고 현재 최대값과 비교하자
	 */
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int T, N;
	static int[][] map;
	static int[] dr = {-1, 1, 0 ,0};	// 상하좌우 4방 탐색
	static int[] dc = {0, 0, -1, 1};
	static int maxVisit;	// 방문 가능한 방의 최대값
	static List<Integer> list;	// 최대값을 가지는 방 번호들 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());

		for(int t=1; t<=T; t++) {
			N = parseInt(br.readLine());
			
			map = new int[N][N];
			maxVisit = 0;
			list = new ArrayList<>();
			
			// 방 번호 입력
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = parseInt(st.nextToken());
				}
			}
			
			// 최대값을 가지는 좌표 찾기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i, j);
				}
			}
			
			// 최대값을 가지는 방 번호들 중 최소값을 출력하기 위해 정렬
			Collections.sort(list);
			sb.append("#").append(t).append(" ").append(list.get(0)).append(" ").append(maxVisit).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int r, int c) {
		int visitNum = 1;	// 시작지점은 방문 횟수 포함
		Queue<Node> queue = new LinkedList<>();
		
		// 시작 노드 Queue에 추가
		queue.add(new Node(r, c));
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				// 현재 좌표에서 4방탐색
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 확인하는 좌표가 배열 범위 내이며 현재 값보다 +1 인경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == map[out.r][out.c]+1) {
					// Queue에 새로운 노드 추가
					queue.offer(new Node(nr, nc));
					// 방문횟수 +1
					visitNum++;
				}
			}
		}
		
		// 방문 수가 최대 방문 수인경우
		if(visitNum == maxVisit) {
			list.add(map[r][c]);	// 방번호 추가
		// 최대 방문 수가 갱신되는 경우
		} else if(visitNum > maxVisit) {
			list.clear();	// 이전 최대 방문수를 가진 방번호들을 초기화
			list.add(map[r][c]); // 새로운 최대 방문 수를 가지는 방번호  추가
			maxVisit = visitNum;	// 최대값 갱신
		}
	}

}
