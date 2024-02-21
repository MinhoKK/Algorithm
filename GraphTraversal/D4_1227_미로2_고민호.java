package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D4_1227_미로2_고민호 {
	/**
	 * @author 고민호
	 * SWEA_1227_미로2
	 * 난이도 D4
	 * 결과 : 통과		메모리 : 26,396KB		시간 : 138ms
	 * 
	 * 풀이방법
	 * 1. BFS를 사용해 2에서 3까지 도달하는지 확인하자!
	 * 2. 미로가 입력될때 2가 입력되면 Queue에 담기
	 * 3. 4방탐색 시 0이면 Queue에 담기, 3이면 탐색 종료
	 */
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int T;
	static int[][] map;
	static Queue<Node> queue;
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			T = parseInt(br.readLine());
			
			map = new int[100][100];
			queue = new LinkedList<>();
			
			
			for(int i=0; i<100; i++) {
				String temp = br.readLine();
				for(int j=0; j<100; j++) {
					map[i][j] = temp.charAt(j) - '0';
					// 2인 경우(시작점)
					if(map[i][j] == 2) {
						queue.add(new Node(i, j));	// BFS 시작점 Queue에 담기
					}
				}
			}
			
			sb.append("#").append(T).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);

	}
	
	static int bfs() {
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			// 상하좌우 4방탐색
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색 좌표가 미로 범위 내, 3인 경우
				if(nr >= 0 && nr < 100 & nc >= 0 && nc < 100 && map[nr][nc] == 3) {
					return 1;	// 실행 가능 return 1 및 종료
				}
				// 탐색 좌표가 미로 범위 내, 0인 경우
				if(nr >= 0 && nr < 100 & nc >= 0 && nc < 100 && map[nr][nc] == 0) {
					map[nr][nc] = 1;	// 다시 탐색하지 않게 1(벽)으로 표시
					queue.add(new Node(nr, nc));	// Queue에 담기
				}
			}
		}
		
		return 0;
	}

}
