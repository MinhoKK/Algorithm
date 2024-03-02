package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_17472_다리만들기2_고민호 {
	/**
	 * @author 고민호
	 * 백준_17472_다리만들기2
	 * 난이도 G1
	 * 결과 : 통과		메모리 : 14,364KB		시간 : 128ms
	 * 
	 * 풀이방법
	 * 1. BFS를 통해 같은 섬 표시하기
	 * 2. 한 방향으로만 다리를 놓을 수 있다 -> DFS를 통해 현재 섬에서 다른 섬 찾기 (다리의 길이 2이상 조건 만족하기)
	 * 3. 모든 섬을 연결하는 최소 다리의 길이 -> 섬 = 노드, 다리의 길이 = 간선의 가중치 라고 생각하여 MST 문제임을 파악하는 것이 중요한 문제!! (1, 2번까지는 쉽게 떠올렸으나 MST문제임을 파악하는데 어려웠던 문제)
	 * 4. 크루스칼 알고리즘을 사용해 모든 섬을 잇는 최소 다리 길이 구하기
	 */
	
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Bridge implements Comparable<Bridge>{
		int from;
		int to;
		int len;
		
		public Bridge(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}
		
		@Override
		public int compareTo(Bridge o) {
			return this.len - o.len;
		}
	}

	static int N, M;
	static int[][] map;
	static ArrayList<Bridge> bridges = new ArrayList<>();
	static int landNum = 2;	// 섬 번호
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		map = new int[N][M];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		// 같은 섬 표시하기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					checkLand(i, j);
				}
			}
		}
		
		// 놓을 수 있는 다리 찾기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] >= 2) {
					// 상하좌우 4방향으로 DFS
					for(int d=0; d<4; d++) {
						findLand(i, j, d, map[i][j], 0);
					}
				}
			}
		}
		
		Collections.sort(bridges);	// 다리 길이 오름차순 정렬
		
		// 크루스칼 알고리즘
		// 부모 노드 초기화
		parent = new int[landNum];
		for(int i=0; i<landNum; i++) {
			parent[i] = i;
		}
		
		// 섬 잇기
		int count = 0;
		for(Bridge bridge : bridges) {
			if(!union(bridge.from, bridge.to)) continue;	// 이미 연결된 경우
			result += bridge.len;	// 연결되지 않은 경우 -> 연결하고 길이 더하기
			if(++count == landNum-3) break;	// 섬의개수-1 만큼 다리를 골랐다면 종료, (landNum이 2로 시작했기에 -3)
		}
		
		// 모든 다리를 확인했지만 섬의개수-1만큼 다리를 고르지 못한 경우 -> 모든 섬이 연결되지 않음
		if(count < landNum-3) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(result);
	}

	/** BFS : 섬 번호 표시 메서드 */
	static void checkLand(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startR, startC));
		
		map[startR][startC] = landNum;
		
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1) {
					map[nr][nc] = landNum;
					queue.add(new Node(nr, nc));
				}
			}
		}
		landNum++;
	}
	
	/** DFS 가능한 다리 만들기 메서드 */
	static void findLand(int startR, int startC, int dir, int nowLand, int len) {
		// DFS 종료 조건 = 시작 섬이 아닌 경우 && 섬인 경우(다른 섬)
		if(map[startR][startC] != nowLand && map[startR][startC] > 0) {
			// 길이가 2이상인 경우 다리 추가
			if(len > 2) {
				bridges.add(new Bridge(nowLand, map[startR][startC], len-1));
			}
			return;
		}
		
		int nr = startR + dr[dir];
		int nc = startC + dc[dir];
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) return;	// 범위를 벗어나는 경우 종료
		
		if(map[nr][nc] == nowLand) return;	// 같은 섬인 경우 종료
		
		findLand(nr, nc, dir, nowLand, len+1);
		
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA == parentB) return false;
		
		parent[parentB] = parentA;
		return true;
	}
}
