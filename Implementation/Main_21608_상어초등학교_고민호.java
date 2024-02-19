package Implementation;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_21608_상어초등학교_고민호 {

	static int N;
	static int[][] students;
	static int[][] map;
	static int[] order;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		N = parseInt(br.readLine());
		
		students = new int[N*N][4];
		map = new int[N][N];
		order = new int[N*N];
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int studentNum = parseInt(st.nextToken()) - 1;
			order[i] = studentNum;	// 자리 앉는 학생 순서
			
			for(int j=0; j<4; j++) {
				students[studentNum][j] = parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N*N; i++) {
			
		}
		
	}
	
	static void checkFavorite(int studentNum) {
		
	}

}
