package GraphTraversal;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_3109_빵집_고민호 {
	/**
	 * @author 고민호
	 * 백준_3109_빵집
	 * 난이도 G2
	 * 결과 : 통과		메모리 : 33,952KB		시간 : 404ms
	 * 
	 * 접근방법
	 * 1. 하나의 시작점에서 DFS를 통해 오른쪽 끝에 도달가능한 길 찾기
	 * 2. 탐색 우선순위는 오른쪽위, 오른쪽, 오른쪽 아래
	 * 3. DFS를 통해 한번 지나간 길은 x로 표시 -> 끝에 도달했다면 파이프라인이 생겨 다른 시작점에서 지나가지 못함, 끝에 도달하지 못했어도 해당 길은 끝에 도달하지 못해 탐색할 필요 X
	 * 4. 끝에 도달한다면 (현재 c값이 C-1인 경우) 파이프라인 개수(result) +1
	 * 
	 * 문제점
	 * 위와 같이 문제를 풀 경우 종료지점에 도달하여 count를 증가시키고 완전히 종료되어 다음 시작점으로 돌아가 새로운 탐색(dfs)를 해야 하지만
	 * 스택에 존재하는 이전 단계로 돌아가 진행가능한 다음 방향이 존재한다면 다시 재귀를 하게됌.
	 * 
	 * 해결책
	 * 현재 탐색에서 끝에 도달한적이 있는지를 확인하는 조건(isEnd)을 추가!!
	 * -> 한번 오른쪽 끝에 도달하여 isEnd=true가 된다면 스택에 존재하는 함수들은 탐색 조건에 의해 더이상 나아가지 못함 
	 * -> 스택에 존재하는 함수들이 더이상 진행되지 않고 스택이 초기화(?) 되고 새로운 시작 좌표에서 dfs시작
	 */

	static int R, C;
	static char[][] map;
	static int[] dr = {-1, 0, 1};	// 오른쪽위, 오른쪽, 오른쪽아래
	static int[] dc = {1, 1, 1};
	static int result;
	static boolean isEnd;	// 오른쪽 끝 도달 확인 변수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = parseInt(st.nextToken());
		C = parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String temp = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			isEnd = false;	// 탐색을 시작할때 오른쪽 끝에 도달 X 상태 
			dfs(i, 0);	// 근처 빵집의 가스관은 지도 맨 왼쪽
		}
		
		System.out.println(result);
	}

	
	static void dfs(int startR, int startC) {
		// 현재 좌표는 지나갔음을 표시
		map[startR][startC] = 'x';
		
		// 원웅이 빵집(가장 오른쪽)에 도달한 경우
		if(startC == C-1) {
			isEnd = true;	// 끝에 도달했음을 표시
			result++;	// 카운트 증가
			return;
		}
		
		
		for(int d=0; d<3; d++) {
			int nr = startR + dr[d];
			int nc = startC + dc[d];
			// 탐색(다음) 좌표가 범위내
			// '.'인 경우
			// 끝에 도달하지 않은 경우
			if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.' && !isEnd) {
				dfs(nr, nc);
			}
		}
	}
}
