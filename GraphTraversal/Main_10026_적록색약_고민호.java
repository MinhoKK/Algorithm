package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_10026_적록색약_고민호 {
	/**
	 * @author 고민호
	 * 백준_10026_적록색약
	 * 결과 : 통과		메모리 : 15,156KB		시간 : 144ms
	 * 난이도 G5
	 * 
	 * 풀이방법
	 * 1. 같은 색인 범위 퍼져가기 -> BFS
	 * 2. 방문 여부를 확인하여 방문하지 않은곳에서만 BFS 진행
	 * 3. BFS를 할때마다 구역 +1
	 * 4. 일반과 적록색약 조건에 맞는 BFS 2개 구현 
	 */

	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;
	static char[][] map;
	static int[][] visit;
	static int person1;	// 일반 구역 수
	static int person2;	// 적록색약 구역 수
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우 4방 탐색
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<temp.length(); j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		
		visit = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문하지 않았다면 BFS 실행
				// 방문한 곳은 이미 구역 존재
				if(visit[i][j] == 0) {
					bfs1(i, j);
					person1++;	// BFS을 실행했다면 구역 수 +1
				}
			}
		}
		
		visit = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 방문하지 않았다면 BFS 실행
				// 방문한 곳은 이미 구역 존재
				if(visit[i][j] == 0) {
					bfs2(i, j);
					person2++;	// BFS을 실행했다면 구역 수 +1
				}
			}
		}
		
		System.out.println(person1);
		System.out.println(person2);
	}

	/** 일반사람의 BFS 메서드 */
	static void bfs1(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(startR, startC));
		visit[startR][startC] = 1;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == 0) {
					// 현재 좌표의 색과 탐색 좌표의 색이 동일한 경우
					if(map[out.r][out.c] == map[nr][nc]) {
						queue.offer(new Node(nr, nc));
						visit[nr][nc] = 1;
					}
				}
			}
		}
	}
	
	/** 적록색약의 BFS 메서드 */
	static void bfs2(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(startR, startC));
		visit[startR][startC] = 1;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == 0) {
					// 현재 좌표가 파랑인 경우
					if(map[out.r][out.c] == 'B') {
						// 탐색 좌표는 파랑으로 동일해야함
						if(map[nr][nc] == 'B') {
							queue.offer(new Node(nr, nc));
							visit[nr][nc] = 1;
						}
					// 현재 좌표가 빨강, 초록인 경우
					} else {
						// 탐색 좌표는 빨강, 초록 둘중 하나만 만족해도됌
						if(map[nr][nc] == 'R' || map[nr][nc] == 'G') {
							queue.offer(new Node(nr, nc));
							visit[nr][nc] = 1;
						}
					}
				}
			}
		}
	}
}
