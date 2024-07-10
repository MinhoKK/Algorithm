package ReTry;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BJ_10026_적록색약 {
    /**
     * @author 고민홉
     * 백준_10026_적록색약
     * 난이도 G5
     * 결과 : 통과      메모리 : 15,416KB      시간 : 140ms
     *
     * 풀이방법
     * 다시 문제를 풀면서 구역의 개수를 어떻게 구해야할지 감을 못잡았다..
     * -> 2차원 배열을 탐색하며 visit이 0이면 BFS 실행, BFS 실행한 횟수 = 구역 개수
     */

    static class Node{
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static char[][] map;
    static int[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());

        map = new char[N][N];

        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = temp.charAt(j);
            }
        }

        int count = 0;
        visit = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visit[i][j] == 0){
                    bfs(i, j);
                    count++;
                }
            }
        }

        sb.append(count).append(" ");

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 'G'){
                    map[i][j] = 'R';
                }
            }
        }

        count = 0;
        visit = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(visit[i][j] == 0){
                    bfs(i, j);
                    count++;
                }
            }
        }

        sb.append(count);
        System.out.println(sb);
    }

    static void bfs(int startR, int startC){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startR, startC));
        visit[startR][startC] = 1;

        while(!queue.isEmpty()){
            Node out = queue.poll();
            for(int d=0; d<4; d++){
                int nr = out.r + dr[d];
                int nc = out.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[out.r][out.c] == map[nr][nc] && visit[nr][nc] == 0){
                    visit[nr][nc] = 1;
                    queue.add(new Node(nr, nc));
                }
            }
        }
    }
}
