package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_15787_기차가어둠을헤치고은하수를_고민호 {
	/**
	 * @author 고민호
	 * 백준_15787_기차가어둠을헤치고은하수를
	 * 난이도 S2
	 * 결과 : 통과		메모리 : 150,356KB		시간 : 688ms
	 * 
	 * 풀이방법 (비트마스킹 문제인지 모르고 풀이, 다음에 비트마스킹으로 풀어보기)
	 * 1. 명령 1,2,3,4를 단순구현
	 * 2. 기차간 중복을 제거하기 위해 HashSet 자료구조 사용
	 * 3. 배열은 레퍼런스 타입으로 중복제거가 되지않아 String Type 으로 변환하여 HashSet에 추가해 중복제거
	 */

	static int N, M;	// 기차 수, 명령 수
	static int[][] train;	// 기차 배열
	static HashSet<String> hashSet = new HashSet<>();	// 기차 중복 제거를 위한 HashSet
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		
		train = new int[N][20];
		
		// 명령수만큼 반복
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int orderNum = parseInt(st.nextToken());
			int trainNum = parseInt(st.nextToken()) - 1;
			int seatNum = 0;
			if(orderNum == 1 || orderNum == 2) {
				seatNum = parseInt(st.nextToken()) - 1;
			}
				
			switch(orderNum) {
				case 1:
					order1(trainNum, seatNum);
					break;
				case 2:
					order2(trainNum, seatNum);
					break;
				case 3:
					order3(trainNum);
					break;
				case 4:
					order4(trainNum);
					break;
			}
		}
		
		// 배열은 레퍼런스 타입으로 같은 배열값을 가져도 참조값이 달라 중복 제거 X
		// -> String Type 으로 변환하여 HashSet에 추가하여 중복제거
		for(int i=0; i<N; i++) {
			hashSet.add(Arrays.toString(train[i]));
		}
		
		
		System.out.println(hashSet.size());

	}
	
	static void order1(int trainNum, int seatNum) {
		if(train[trainNum][seatNum] == 0) {
			train[trainNum][seatNum] = 1;
		}
	}
	
	static void order2(int trainNum, int seatNum) {
		if(train[trainNum][seatNum] == 1) {
			train[trainNum][seatNum] = 0;
		}
	}
	
	static void order3(int trainNum) {
		if(train[trainNum][19] == 1)
			train[trainNum][19] = 0;
		
		for(int i=18; i>=0; i--) {
			train[trainNum][i+1] = train[trainNum][i];
		}
		
		train[trainNum][0] = 0;
	}
	
	static void order4(int trainNum) {
		if(train[trainNum][0] == 1)
			train[trainNum][0] = 0;
		
		for(int i=1; i<20; i++) {
			train[trainNum][i-1] = train[trainNum][i];
		}
		train[trainNum][19] = 0;
	}
}
