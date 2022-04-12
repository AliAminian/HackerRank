import java.util.*;

public class CountTreesOfForest {
    static class Connection {
        private int C;
        private LinkedList<Integer>[] adj;

        Connection(int c) {
            this.C = c;

            adj = new LinkedList[C];

            for (int i = 0; i < C; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        void addRelation(int v, int w) {
            adj[v].add(w);
        }

        void traverse(int v, boolean visited[]) {
            visited[v] = true;

            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) traverse(n, visited);
            }
        }

        int countRelations() {
            boolean visited[] = new boolean[C];
            int res = 0;

            for (int i = 0; i < this.C; i++) {
                if (!visited[i]) {
                    traverse(i, visited);
                    res++;
                }
            }
            return res;
        }

    }

    public static void main(String[] args) {
        Connection connections = new CountTreesOfForest.Connection(4) ;
        connections.addRelation(0, 1);
        connections.addRelation(1, 0);
        connections.addRelation(1, 2);
        connections.addRelation(2, 1);
        connections.addRelation(0, 1);
        System.out.println(connections.countRelations());
    }

}
