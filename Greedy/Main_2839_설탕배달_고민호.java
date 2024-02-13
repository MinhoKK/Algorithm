package Greedy;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_2839_설탕배달_고민호 {
	/**
	 * @author 고민호
	 * 백준_2839_설탕배달
	 * 난이도 S4
	 * 결과 : 통과		메모리 : 14,192KB		시간 : 124ms
	 * 
	 * 풀이방법
	 * 1. 적은 봉지로 설탕을 담기 위해선 5kg 봉지를 최대한 많이 사용하기 -> 우선적으로 5로 나누어 떨어지는지 확인
	 * 2. 나누어 떨어진다면 몫이 최소 봉지수
	 * 3. 나누어 떨어지지 않는다면 3kg 봉지 한개 사용 -> 총 설탕무게 - 3
	 * 4. 2 3번을 반복하며 봉지 수 확인하다 설탕 무게가 1, 2kg가 남는다면 불가능한 무게로 -1 출력
	 */

	static int N;
	static int count;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = parseInt(br.readLine());

		while(N > 0) {
			// 5kg봉지 사용
			if(N % 5 == 0) {
				count += N / 5;
				N = N % 5;
				break;
			}
			// 설탕 무게가 1, 2kg가 남는다면 불가능한 무게
			if(N < 3) {
				System.out.println(-1);
				return;
			}
			// 3kg봉지 하나 사용하기
			N -= 3;
			count++;
			
			
		}
		
		System.out.println(count);
		
	}

}
