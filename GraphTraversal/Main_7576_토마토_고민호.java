package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_7576_토마토_고민호 {
	/**
	 * @author 고민호
	 * 백준_7576_토마토
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 120,172KB		시간 : 632ms
	 * 
	 * 풀이방법
	 * 1. 입력 과정에서 토마토(1)가 입력되면 Queue에 offer
	 * 2. 1인 좌표에서 BFS 시작
	 * 3. 이때 노드 클래스는 좌표 r, c와 언제 익었는지 day 변수를 가진다
	 * 4. day변수는 자신에게 영향을 준 토마토의 day + 1 
	 * 5. BFS 종료 후 상자에 0이 존재하면 -1 출력 없다면 최종 day 출력
	 */
	static class Node{
		int r;
		int c;
		int day;
		
		public Node(int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	static int N, M;
	static int[][] map;
	static Queue<Node> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우 4방탐색
	static int[] dc = {0, 0, -1, 1};
	static int result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = parseInt(st.nextToken());
		N = parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
				// BFS를 위해 토마토 좌표 queue 담기
				if(map[i][j] == 1) {
					queue.offer(new Node(i, j, 0));
				}
			}
		}

		// BFS 실행
		bfs();
		
		// 0(익지않은 토마토)가 존재하는 경우
		exit:
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					sb.append(-1);	// -1 출력
					break exit;
				}
			}
		}
		
		// 위의 for문에서 0이 없는 경우 최소 날짜 출력
		if(sb.length() == 0) sb.append(result);
		
		System.out.println(sb);
	}
	
	static void bfs() {
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색 좌표가 범위 내, 익지않은 토마토인 경우
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
					map[nr][nc] = 1;	// 익은 토마토로 바꾸기
					queue.offer(new Node(nr, nc, out.day + 1));	// 새로 익은 토마토는 자신에게 영향을 준 토마토의 day + 1
					result = out.day + 1;	// 현재 날짜(result) = 최소 날짜 갱신
				}
			}
		}
	}

}
