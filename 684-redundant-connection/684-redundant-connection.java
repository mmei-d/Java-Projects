class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1]; 
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            if(find(parent, parents) == find(child, parents)) {
                return edge;
            }

            union(parent, child, parents);
        }

        return null;
    }

    private int find(int node, int[] parents) {
        while(node != parents[node]) {
            node = parents[node];
        }
        return node;
    }

    private void union(int a, int b, int[] parents) {
        int aRoot = find(a, parents);
        int bRoot = find(b, parents);
        if(aRoot == bRoot) return;
        parents[aRoot] = bRoot;
    }
}