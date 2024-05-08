import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourcetoTarget {

    /* backtrack */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(path, res, graph, 0);
        return res;
    }

    public void dfs(List<Integer> path, List<List<Integer>> res, int[][] graph, int node) {
        path.add(node);
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        for (int v : graph[node])
            dfs(path, res, graph, v);
        path.remove(path.size() - 1);
    }

    /* dfs, less efficient */
    int n;
    int[][] graph;

    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        this.n = graph.length - 1;
        this.graph = graph;
        // scan through each neighbour of 0
        for (int i = 0; i < graph[0].length; i++) {
            List<List<Integer>> partial = dfs(graph[0][i], new ArrayList<>());
            for (int j = 0; j < partial.size(); j++) {
                if (partial.get(j).size() > 0)
                    partial.get(j).add(0, 0);
            }
            res.addAll(partial);
        }
        return res;
    }

    private List<List<Integer>> dfs1(int node, List<Integer> route) {
        // base case
        if (node == n) {
            List<List<Integer>> l = new ArrayList<>();
            route.add(n);
            l.add(route);
            return l;
        }

        // recursive case
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < graph[node].length; i++) {
            List<List<Integer>> partial = dfs(graph[node][i], new ArrayList<>());
            for (int j = 0; j < partial.size(); j++) {
                if (partial.get(j).size() > 0)
                    partial.get(j).add(0, node);
            }
            res.addAll(partial);
        }
        System.out.println(res);
        return res;
    }
}
