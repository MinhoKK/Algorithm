package Implementation;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main_16935_배열돌리기3_고민호 {
	/**
	 * @author 고민호
	 * 백준_16935_배열돌리기3
	 * 난이도 G5
	 * 결과 : 통과		메모리 : 65,672KB		시간 : 776ms
	 * 
	 * 풀이방식
	 * 1. 각 연산들을 구현하기
	 * 2. 구현 시 기존배열과 동일한 크기의 임시배열을 만들어 값을 저장하기
	 * 3. switch문을 통해 연산 수행하기
	 */
	static int N, M, R;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = parseInt(st.nextToken());
		M = parseInt(st.nextToken());
		R = parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			// 수행할 연산 입력받기
			int funcNum = parseInt(st.nextToken());
				switch(funcNum) {
					case 1:
						func1();
						break;
					case 2:
						func2();
						break;
					case 3:
						func3();
						break;
					case 4:
						func4();
						break;
					case 5:
						func5();
						break;
					case 6:
						func6();
						break;
			}
		}
	
		// 배열 출력하기
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static void func1() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] temp = new int[n][m];
		
		// 열은 고정되고 행을 역순으로 저장한다
		for(int i=0; i<n; i++) {
			temp[n-1-i] = arr[i];
		}
		
		arr = temp;
	}
	
	static void func2() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] temp = new int[n][m];
		
		// 행은 고정되고 열을 역순으로 저장한다
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				temp[j][m-1-i] = arr[j][i];
			}
		}
		arr = temp;
	}
	
	static void func3() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] temp = new int[m][n];
		
		// 기존 배열의 행이 열이 되고, 기존 배열의 n - 1 - 행이 열이된다.
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[j][n-1-i] = arr[i][j];
			}
		}
		arr = temp;
	}
	
	static void func4() {
		int n = arr.length;
		int m = arr[0].length;
		int[][] temp = new int[m][n];
		
		// 기존 배열의 열이 행이 되고, 기존 배열의 m - 1 - 열이 행이된다.
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[m-1-j][i] = arr[i][j];
			}
		}
		arr = temp;
	}
	
	static void func5() { 
		int n = arr.length;
		int m = arr[0].length;
		int halfN = n/2;
		int halfM = m/2;
		int[][] temp = new int[n][m];
		
		// 1->2
		for(int i=0; i<halfN; i++) {
			for(int j=0; j<halfM; j++) {
				temp[i][j+halfM] = arr[i][j];
			}
		}
		// 2->3
		for(int i=0; i<halfN; i++) {
			for(int j=halfM; j<m; j++) {
				temp[i+halfN][j] = arr[i][j];
			}
		}
		// 3->4
		for(int i=halfN; i<n; i++) {
			for(int j=halfM; j<m; j++) {
				temp[i][j-halfM] = arr[i][j];
			}
		}
		// 4->1
		for(int i=halfN; i<n; i++) {
			for(int j=0; j<halfM; j++) {
				temp[i-halfN][j] = arr[i][j];
			}
		}
		arr = temp;
	}
	
	static void func6() {
		int n = arr.length;
		int m = arr[0].length;
		int halfN = n/2;
		int halfM = m/2;
		int[][] temp = new int[n][m];
		
		// 4->1
		for(int i=0; i<halfN; i++) {
			for(int j=0; j<halfM; j++) {
				temp[i+halfN][j] = arr[i][j];
			}
		}
		// 4->3
		for(int i=halfN; i<n; i++) {
			for(int j=0; j<halfM; j++) {
				temp[i][j+halfM] = arr[i][j];
			}
		}
		// 3->2
		for(int i=halfN; i<n; i++) {
			for(int j=halfM; j<m; j++) {
				temp[i-halfN][j] = arr[i][j];
			}
		}
		// 2->1
		for(int i=0; i<halfN; i++) {
			for(int j=halfM; j<m; j++) {
				temp[i][j-halfM] = arr[i][j];
			}
		}
		arr = temp;
	}
}
