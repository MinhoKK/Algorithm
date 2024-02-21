package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15686_치킨배달_고민호 {
	/**
	 * @author 고민호
	 * 백준_15686_치킨배달
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 12,504KB		시간 : 180ms
	 * 
	 * 풀이방법
	 * 1. 도시 정보 (0, 1, 2)을 받을때 치킨집 위치를 저장하기 위해 list를 만들어 2가 입력되면 해당 좌표 Node class로 저장하기
	 * 2. 재귀를 통해 M개의 치킨집 조합 만들기
	 * 3. 도시 2차원 배열을 완전 탐색하며 집(1)인경우 조합에 존재하는 치킨집과의 거리 중 가장 가까운 거리들을 전부 합하기
	 * 4. 3번에서 구한값과 이전 조합에서 구한 값들을 비교하며 모든 치킨집 조합 중 거리 최소값 구하기
	 */

	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	
	static int N, M;	// 배열 크기, 치킨집 최대 개수
	static int[][] map;	// 도시 정보 배열
	static ArrayList<Node> chickens = new ArrayList<>();	// 치킨집 위치 저장 리스트
	static int[] comb;	// 치킨집 조합 배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		map = new int[N][N];
		comb = new int[M];	// 치킨 조합 개수는 M개
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = parseInt(st.nextToken());
				// 치킨집인 경우
				if(map[i][j] == 2) {
					chickens.add(new Node(i, j));	// 치킨집 위치 저장
				}
			}
		}
		
		
		makeComb(0, 0);	// 치킨집은 1개  ~ M개 존재 가능 -> 1개부터 M개까지의 조합 구하기
		
		
		System.out.println(result);
	}
	
	static void makeComb(int depth, int start) {
		// 조합이 완성된 경우
		if(depth == M) {
			checkDis();
			return;
		}
		
		// 치킨집 조합 만들기
		for(int i=start; i<chickens.size(); i++) {
			comb[depth] = i;
			makeComb(depth+1, i+1);
		}
	}

	/** 현재 조합에서 최소 거리 구하기 */
	static void checkDis() {
		int distance = 0;	// 모든 집에서 각자 가장 가까운 치킨집까지의 총 거리합
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 집인 경우
				if(map[i][j] == 1) {
					int tempDis = Integer.MAX_VALUE;
					// 조합에 존재하는 치킨집 중 현재 집과 가장 가까운 치킨집과의 거리 구하기
					for(int index : comb) {
						tempDis= Math.min(tempDis, Math.abs(chickens.get(index).r - i) + Math.abs(chickens.get(index).c - j));
					}
					distance += tempDis;
					if(distance > result) return; // 이전 조합까지의 최소거리보다 커지면 종료
				}
			}
		}
		
		result = Math.min(result, distance); // 최소값 갱신
		
	}
	
	
}
