package 다시풀기;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BJ_2573 {

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visit;
    static int countIce;
    static int day;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }

        while(true){
            melt();
            checkIce();

            day ++;
            if(countIce == 0){
                day = 0;
                break;
            }

            if(countIce >= 2){
                break;
            }
        }

        System.out.println(day);
    }

    static void melt(){
        int[][] tempMap = new int[N][M];
        for(int i=0; i<N; i++){
            tempMap[i] = map[i].clone();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] > 0){
                    int countSea = 0;

                    for(int d=0; d<4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(isIn(nr, nc) && map[nr][nc] == 0) countSea++;
                    }

                    tempMap[i][j] -= countSea;
                    if(tempMap[i][j] < 0) tempMap[i][j] = 0;
                }
            }
        }

        map = tempMap;
    }

    static void checkIce(){
        countIce = 0;
        visit = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] > 0 && visit[i][j] == 0){
                    bfs(i, j);
                    countIce++;
                }
            }
        }
    }

    static void bfs(int r, int c){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(r, c));
        visit[r][c] = 1;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            for(int d=0; d<4; d++){
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if(isIn(nr, nc) && map[nr][nc] > 0 && visit[nr][nc] == 0){
                    visit[nr][nc] = 1;
                    queue.add(new Node(nr, nc));
                }
            }
        }
    }

    static boolean isIn(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
