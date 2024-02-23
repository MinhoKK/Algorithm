package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_17281_야구_고민호 {
	/**
	 * @author 고민호
	 * 백준_17281_야구
	 * 난이도 G4
	 * 결과 : 통과		메모리 : 79,028KB		시간 : 568ms
	 * 
	 * 풀이방법
	 * 1. 열심히 구현하기..
	 * 2. 타자 순서를 정할때 주어진 조건, 1번선수는 반드시 4번 타자를 위해  dfs 종료 조건 추가하기
	 * 3. 점수 획득을 위하여 크기 3인 1차원 배열을 만들어 1루, 2루, 3루에 선수가 있는지 확인하기
	 * 4. 안타라면 1베이스 , 2루타라면 2베이스, 3루타라면 3베이스 밀어내기, 홈런일경우 베이스에 존재하는 선수 + 자신만큼 점수증가
	 */

	static int N;	// 이닝 수
	static int[] comb;	// 선수 번호  조합
	static int[] visit;
	static int[][] person;	// 타자 별 이닝 결과
	static int maxPoint = Integer.MIN_VALUE;
	static int nowPerson = 0;	// 현재 타자 순서 (1~9)
	static int[] base;	// 1, 2, 3루 베이스
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = parseInt(br.readLine());
		comb = new int[9];	// 타자 9명 고정
		visit = new int[9];
		person = new int[N][9];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				person[i][j] = parseInt(st.nextToken());
			}
		}
		
		makeComb(0);
		System.out.println(maxPoint);
	}
	
	
	static void makeComb(int depth) {
		if(depth == 9 && comb[3] == 0) {	// 타자 순서 조합 시 1번 선수는 반드시 4번 타자 고정
			nowPerson = 0;	// 타자 번호, 조합이 완성되어 경기를 시작한다면 타자는 0번 부터
			checkPoint();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visit[i] == 0) {
				visit[i] = 1;
				comb[depth] = i;
				makeComb(depth+1);
				visit[i] = 0;
			}
		}
	}

	static void checkPoint() {
		int point = 0;	// 이번 타자 조합으로 만들어내는 점수
		for(int i=0; i<N; i++) {
			int outCount = 0;	// 아웃 카운트는 이닝마다 초기화
			base = new int[3];	// 베이스는 이닝마다 초기화
			// 아웃 카운트가 3이상인 경우 이닝 종료
			while(outCount < 3) {
				int now = comb[nowPerson++];	// 현재 타자의 선수 번호
				if(nowPerson==9) nowPerson = 0;	// 타자가 9번 돌면 다시 1번으로
					
				switch(person[i][now]) {	// i = 이닝, now = 현재 타자의 선수 번호
					case(1):
						point = doOne(point);
						break;
					case(2):
						point = doTwo(point);
						break;
					case(3):
						point = doThree(point);
						break;
					case(4):
						point = doFour(point);
						break;
					case(0):
						outCount++;
						break;
				}
			}
		}
		
		maxPoint = Math.max(maxPoint, point);
	}
	
	/** 1루타 메소드 */
	static int doOne(int point) {
		// 3루, 2루, 1루 순서대로 베이스 확인하기
		for(int i=2; i>=0; i--) {
			if(base[i] == 1) {	// 베이스에 선수가 있는 경우
				// 홈으로 오는 경우
				if(i+1>2) {
					point++;	// 점수 올리기
					base[i] = 0;	// 베이스 비우기
				// 홈으로 오지 못하는 경우
				} else {
					base[i+1] = 1;	// 한 베이스 앞으로
					base[i] = 0;	// 베이스 비우기
				}
			}
			
		}
		base[0] = 1;	// 1루에는 무조건 진루
		return point;
	}
	
	/** 2루타 메소드 */
	static int doTwo(int point) {
		for(int i=2; i>=0; i--) {
			if(base[i] == 1) {
				// 홈으로 오는 경우
				if(i+2>2) {
					point++;	// 점수 올리기
					base[i] = 0;	// 베이스 비우기
				// 홈으로 오지 못하는 경우
				} else {	
					base[i+2] = 1;	// 두 베이스 앞으로
					base[i] = 0;	// 베이스 비우기
				}
			}
			
		}
		base[1] = 1;	// 2루에는 무조건 진루
		return point;
		
	}

	/** 3루타 메소드 */
	static int doThree(int point) {
		for(int i=2; i>=0; i--) {
			if(base[i] == 1) {
				// 홈으로 오는 경우
				if(i+3>2) {
					point++;	// 점수 올리기
					base[i] = 0;
				// 홈으로 오지 못하는 경우
				} else {	
					base[i+3] = 1;	// 세 베이스 앞으로
					base[i] = 0;	// 베이스 비우기
				}
			}
			
		}
		base[2] = 1;	// 3루에는 무조건 진루
		return point;
	
	}

	/** 홈런 메소드 */
	static int doFour(int point) {
		for(int i=0; i<=2; i++) {
			if(base[i] == 1) {
				base[i] = 0;	// 베이스 비우기
				point++;	// 주자가 있다면 점수
			} 
		}
		point++;	// 자기 자신 점수
		return point;
	}
}
