package String;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class D3_1228_암호문1_고민호 {

	static int N;	// 암호문의 길이
	static List<Integer> list;	// 원본 암호문
	static int orderNum;	// 명령어의 개수
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	// I로 문자열을 구분하기 위한 StringTokenizer
		StringTokenizer st2;	// 공백으로 문자열을 구분하기 위한 StringTokenizer
		
		// 테스트 케이스는 10개 고정
		for(int t=1; t<=10; t++) {
			// 테스트 케이스별 초기화
			N = parseInt(br.readLine());	// 암호문 길이 초기화
			list = new ArrayList<Integer>();	// 원본 암호문 배열 생성
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				list.add(parseInt(st.nextToken()));	// 원본 암호문 초기화
			}
			
			orderNum = parseInt(br.readLine());	// 명령어 개수 초기화
			st = new StringTokenizer(br.readLine(), "I");	// I를 기준으로 문자열 나누기
			// 명령어의 개수만큼 I를 기준으로 나눠진 상태
			for(int i=0; i<orderNum; i++) {
				String temp = st.nextToken();	// temp에는 x, y, s 정보 존재
				st2 = new StringTokenizer(temp);	// temp 문자열을 공백으로 구분
				int start = parseInt(st2.nextToken());	// 첫 토큰은 x (위치)
				int num = parseInt(st2.nextToken());	// 두번째 토큰은 y (덧붙일 숫자 개수)
				// 덧붙일 숫자 만큼 반복
				for(int j=0; j<num; j++) {
					list.add(start++, parseInt(st2.nextToken()));	// list에 숫자 삽입
				}
			}
			
			// 수정된 문자열(암호문)의 처음 10개 출력하기
			sb.append("#").append(t).append(" ");
			for(int i=0; i<10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
