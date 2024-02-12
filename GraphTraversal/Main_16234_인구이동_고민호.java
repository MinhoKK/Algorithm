package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16234_인구이동_고민호 {
	/**
	 * @author 고민호
	 * 백준_16234_고민호
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 302,513KB		시간 : 976ms
	 * 
	 * 풀이방법
	 * 1. 조건에 만족하는 나라를 찾자! -> BFS 사용
	 * 2. 방문 가능한 나라는 방문표시하기
	 * 3. 단 방문여부뿐만이 아닌 엽합을 구분해주기 -> visit 배열에 방문했다면 1, 2, 3같이 숫자를 늘려가며 연합을 표시해줌
	 *    해당 로직을 생각해내는데 시간이 오래걸렸습니다(visit배열 0은 인구이동이 일어나지 않는 곳)
	 * 4. visit 배열을 탐색해 같은 숫자(연합)인 곳 인구이동 시키기   
	 * 
	 */
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N, L, R;
	static int[][] map;	// 지도 배열
	static int[][] visit;	// 방문 여부 배열
	static int visitCount;	// 인구 이동이 일어나는 나라 수
	static int teamNum;	// 연합 번호
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static int day;
	static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		L = parseInt(st.nextToken());
		R = parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
			}
		}
		
		
		while(true) {
			visitCount = 0;	// 인구 이동이 일어나는 나라 수
			visit = new int[N][N];
			teamNum = 1;	// 연합은 1번 부터
			
			// BFS 수행
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i, j);
				}
			}
			
			if(visitCount == 0) break;	// 인구이동이 없는 경우
			
			// 연합 번호 확인하기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 연합인 경우
					if(visit[i][j] != 0) {
						set.add(visit[i][j]); // set 자료구조를 사용해 연합 중복 제거
					}
				}
			}
			
			// 연합 별 인구 이동
			for(int team : set) {
				int peopleNum = 0;
				int nationNum = 0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						// 연합 별 인구수, 국가 수 확인
						if(visit[i][j] == team) {
							peopleNum += map[i][j];
							nationNum++;
						}
					}
				}
				
				// 인구 이동
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(visit[i][j] == team) {
							map[i][j] = peopleNum/nationNum;
						}
					}
				}
			}
			

			day++;
		}
		
		System.out.println(day);
	}

	
	static void bfs(int startR, int startC) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startR, startC));
		
		int checkTeam = 0;
		while(!queue.isEmpty()) {
			Node out = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = out.r + dr[d];
				int nc = out.c + dc[d];
				// 탐색하는 좌표가 범위 내
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == 0) {
					// 현재 좌표와 탐색 좌표의 인구 수 차(절대값) 조건 만족
					if(Math.abs(map[out.r][out.c] - map[nr][nc]) >= L && Math.abs(map[out.r][out.c] - map[nr][nc]) <= R) {
						queue.add(new Node(nr, nc));
						visit[nr][nc] = teamNum;	// 연합 번호로 방문 기록
						visitCount++;	// 인구 이동 나라 수 증가
						checkTeam++;
					}
				}
			}
		}
		// 인구 이동 발생
		if(checkTeam > 0)	
			teamNum++;	// 다음 bfs에선 연합 번호 증가
	}
}
