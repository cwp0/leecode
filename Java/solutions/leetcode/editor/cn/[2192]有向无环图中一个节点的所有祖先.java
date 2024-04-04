// 
// @Question: [2192]有向无环图中一个节点的所有祖先 
// @Author: cwp0
// @CreatedTime: 2024-04-04 15:32:04
// @Description: 
//给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。 
//
// 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。 
//
// 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。 
//
// 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
//输出：[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
//解释：
//上图为输入所对应的图。
//- 节点 0 ，1 和 2 没有任何祖先。
//- 节点 3 有 2 个祖先 0 和 1 。
//- 节点 4 有 2 个祖先 0 和 2 。
//- 节点 5 有 3 个祖先 0 ，1 和 3 。
//- 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
//- 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
// 
//
// 示例 2： 
//
// 
//
// 输入：n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[
//3,4]]
//输出：[[],[0],[0,1],[0,1,2],[0,1,2,3]]
//解释：
//上图为输入所对应的图。
//- 节点 0 没有任何祖先。
//- 节点 1 有 1 个祖先 0 。
//- 节点 2 有 2 个祖先 0 和 1 。
//- 节点 3 有 3 个祖先 0 ，1 和 2 。
//- 节点 4 有 4 个祖先 0 ，1 ，2 和 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 0 <= edges.length <= min(2000, n * (n - 1) / 2) 
// edges[i].length == 2 
// 0 <= fromi, toi <= n - 1 
// fromi != toi 
// 图中不会有重边。 
// 图是 有向 且 无环 的。 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 57 👎 0

import java.util.*;

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // 图的临接表
        List<Integer>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        // 入度数组，记录每个节点的入度
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            // 根据边来构建临接表
            e[edge[0]].add(edge[1]);
            // 节点的入度+1
            indegree[edge[1]]++;
        }

        // 存储每个节点祖先的数组
        Set<Integer>[] ancestors = new Set[n];
        for (int i = 0; i < n; i++) {
            ancestors[i] = new HashSet<Integer>();
        }
        // 广度优先遍历求解拓扑排序？？
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            // 从入度为0的节点开始遍历
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : e[u]) {
                // u -> v ，则u是v的祖先
                ancestors[v].add(u);
                for (int i : ancestors[u]) {
                    // u的祖先也是v的祖先
                    ancestors[v].add(i);
                }
                indegree[v]--;
                // 如果v节点的入度为0了，则将其加入q队列。
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        // 转换为答案数组
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<Integer>());
            for (int j : ancestors[i]) {
                res.get(i).add(j);
            }
            Collections.sort(res.get(i));
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
