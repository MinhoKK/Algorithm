package Combination;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D3_6808_규영이와인영이의카드게임_고민호 {
	/**
	 * @author 고민호
	 * SWEA_6808_규영이와인영이의카드게임
	 * 난이도 D3
	 * 결과 : 통과		메모리 : 21,500KB		시간 : 3,952ms
	 * 
	 * 풀이방법
	 * 1. 인영이 카드 List를 1~18 채워두고, 규영이 카드가 주어져 List를 채울때 해당 카드번호를 인영이 카드 List에서 제거
	 * 2. 인영이 카드 List에 존재하는 카드 9장으로 만들 수 있는 카드 조합 만들기
	 * 3. 조합이 완성되면 for문을 돌며 규영이 카드와 비교해 점수내기
	 */

	static int T;
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;
	static int win;
	static int lose;
	static int[] comb;
	static int[] visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			listA = new ArrayList<>();	// 규영이 카드 리스트
			listB = new ArrayList<>();	// 인영이 카드 리스트
			
			// 인영이 카드 1~18 채워두기
			for(int i=1; i<=18; i++) {
				listB.add(i);
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				listA.add(parseInt(st.nextToken()));	// 규영이 카드 입력
				listB.remove(listA.get(i));	// 규영이 카드 번호를 인영이 카드 리스트에서 제거
			}
			
			win = 0;
			lose = 0;
			comb = new int[9];
			visit = new int[9];
			
			makeComb(0);
			
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void makeComb(int depth) {
		if(depth == 9) {
			int pointA = 0;	// 규영이 점수
			int pointB = 0; // 인영이 점수
			for(int i=0; i<9; i++) {
				// 규영이가 이긴 경우
				if(listA.get(i) > listB.get(comb[i])) {
					pointA += listA.get(i) + listB.get(comb[i]); 
				// 인영이가 이긴 경우	
				} else {
					pointB += listA.get(i) + listB.get(comb[i]);
				}
			}
			// 규영이 점수가 높은 경우 승
			if(pointA > pointB) {
				win++;
			// 인영이 점수가 높은 경우 패
			} else if(pointA < pointB) {
				lose++;
			}
			
			return;
		}
		
		// 인영이 카드 조합 만들기
		for(int i=0; i<9; i++) {
			if(visit[i] == 0) {
				visit[i] = 1;
				comb[depth] = i;
				makeComb(depth+1);
				visit[i] = 0;
			}
		}
		
	}

}
