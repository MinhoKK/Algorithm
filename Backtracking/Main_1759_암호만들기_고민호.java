package Backtracking;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_1759_암호만들기_고민호 {
	/**
	 * @author 고민호
	 * 백준_1759_암호만들기
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 14,896KB		시간 : 132ms
	 * 
	 * 풀이방법
	 * 1. L크기의 문자 조합을 만들기
	 * 2. 조합이 완성되면 모음과 자음의 개수 구하기
	 * 3. 모음이 1개 미안, 자음이 2개 미만이라면 불가능한 조합으로 return
	 * 
	 * 개선점
	 * 1. 처음에 L개의 문자가 주어지고 C개의 문자로 조합이 완성된 후 모음, 자음 수에 대한 조건을 확인하여 만족하면 이후에 문자조합으로 만들어지는 문자들을 오름차순 했었다..
	 * 2. 하지만 L개의 문자가 주어지자마자 오른차순으로 정렬 후 조합을 만들면 1번과 같이 복잡하게 하지 않아도 된다....
	 * 3. 1, 2번 성능차이는 크게 나지는 않지만 다음부터는 오름차순 문제가 나오면 일단 초기 데이터를 정렬해야할지 말아야할지 고민을 먼저 해보는 습관을 가지자!!!
	 */

	static int L, C;
	static char[] chars;
	static int[] comb;
	static List<Character> moeum = Arrays.asList('a', 'e', 'i', 'o', 'u');
	static int countMo;	// 최소 1개
	static int countJa;	// 최소 2개
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = parseInt(st.nextToken());	// 암호 길이
		C = parseInt(st.nextToken());	// 주어지는 문자 개수
		
		comb = new int[L];
		chars = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			chars[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(chars);	// 초기 데이터 오름차순 정렬
		
		dfs(0, 0);
		
		System.out.println(sb);
	}
	
	static void dfs(int depth, int start) {
		// 조합이 완성된 경우
		if(depth == L) {
			countMo = 0;
			countJa = 0;
			countMoJa();	// 조합에 구성된 알파벳들의 모음, 자음 개수 확인하기
			if(countMo < 1 || countJa < 2) return;	// 조건을 만족하지 못하면 종료!!
			
			for(int index : comb) {
				sb.append(chars[index]);
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<C; i++) {
			comb[depth] = i;
			dfs(depth+1, i+1);
		}
		
		
	}

	static void countMoJa() {
		for(int c : comb) {
			if(moeum.contains(chars[c])) {	// 모음 a, e, i, o, u에 문자가 포함되는 경우
				countMo++;
			} else {	// 포함되지 않는 경우
				countJa++;
			}
		}
	}
}
