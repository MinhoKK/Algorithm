package Temp;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ_1043_거짓말 {

    static int N, M;    // 사람의 수, 파티의 수
    static int[] know;
    static int[] parent;
    static List<List<Integer>> parties = new ArrayList<>();
    static int result;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());   // 사람 수
        M = parseInt(st.nextToken());   // 파티 수

        know = new int[N + 1];
        parent = new int[N + 1];

        // 부모 노드 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int knowCount = parseInt(st.nextToken());   // 사실을 아는 사람 수

        // 사실을 아는 사람 번호 입력받기
        for (int i = 0; i < knowCount; i++) {
            int value = parseInt(st.nextToken());
            know[value] = 1;    // 사실을 아는 사람 1 표시
        }

        // 파티 정보 저장
        for (int i = 0; i < M; i++) {
            parties.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int personCount = parseInt(st.nextToken()); // 파티 참여 인원 수
            for (int j = 0; j < personCount; j++) {
                parties.get(i).add(parseInt(st.nextToken())); // 파티에 참여한 사람 번호 입력
            }
        }

        // 모든 파티를 순회하며 한 파티에 동시에 참여하는 사람들 union 해주기
        // 연결되는(만나는) 사람들을 확인하기 위함
        for (List<Integer> party : parties) {
            for (int i = 0; i < party.size() - 1; i++) {
                union(party.get(i), party.get(i + 1));
            }
        }

        for(int i=1; i<N+1; i++){
            if(know[i] == 1){   // 사실을 아는 경우
                know[parent[i]] = 1;  // 나의 부모노드도 사실을 알게됨, 1로 표시
            }
        }

        for(int i=1; i<N+1; i++){
            if(know[parent[i]] == 1){   // 사실을 아는 경우
                know[i] = 1;  // 자식노드도 사실을 알게됨
            }
        }

        // 파티에 사실을 아는 사람이 한명도 없으면 거짓말 count 증가
        for(List<Integer> party : parties){
            int count = 0;  // 해당 파티에서 사실을 아는 사람 수
            for (int personNum : party) {
                if (know[parent[personNum]] == 1) {
                    count++;
                }
            }
            if(count == 0) result++;
        }

        System.out.println(result);
    }

    static int find(int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        parent[parentB] = parentA;  // a의 부모노드를 b의 부모노드로 붙이기
    }
}
