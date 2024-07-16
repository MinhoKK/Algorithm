package DynamicProgramming;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_1890_점프 {
    /**
     * @author 고민호
     * 백준_1890_점프
     * 난이도 S1
     * 결과 : 통과      메모리 : 14,292KB      시간 : 104ms
     *
     * 풀이방법
     * 1. 2차원 배열을 순차적으로 순회하며 현재 좌표에서 이동 가능한 좌표 찾기
     * 2. 이동가능한 좌표의 방문 수 = 현재 좌표의 방문 수 + 이동 가능한 좌표의 방문 수
     */

    static int N;
    static int[][]  map;
    static long[][] visit;
    static int[] dr = {1, 0};  // 하, 우
    static int[] dc = {0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());

        map = new int[N][N];
        visit = new long[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }

        visit[0][0] = 1;    // 시작 좌표 1로 초기화 -> 방문한 것으로 표기하기 위해

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){

                // 종료 지점에 도착한 경우
                if(map[i][j] == 0){
                    System.out.println(visit[N-1][N-1]);
                    return;
                }

                // 방문을 한 경우
                if(visit[i][j] > 0){
                    // 우측, 하단 방향으로 이동 가능한지 확인하기
                    for(int d=0; d<2; d++){
                        int nr = i + (dr[d]*map[i][j]);
                        int nc = j + (dc[d]*map[i][j]);

                        // 범위 내로 이동이 가능한 경우라면
                        if(nr >= 0 && nr < N && nc >= 0 && nc < N){
                            visit[nr][nc] += visit[i][j];   // 이동 가능 좌표까지의 경우의 수 = 이동 가능 좌표까지의 경우의 수 + 현재 좌표까지의 경우의 수
                        }
                    }
                }

            }
        }
    }
}
