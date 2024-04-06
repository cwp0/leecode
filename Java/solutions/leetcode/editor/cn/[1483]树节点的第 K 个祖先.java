// 
// @Question: [1483]树节点的第 K 个祖先 
// @Author: cwp0
// @CreatedTime: 2024-04-06 16:57:43
// @Description: 
//给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0
// 的节点。 
//
// 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。 
//
// 实现 TreeAncestor 类： 
//
// 
// TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。 
// getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：
//["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
//[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
//
//输出：
//[null,1,0,-1]
//
//解释：
//TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
//
//treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
//treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
//treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 5 * 10⁴ 
// parent[0] == -1 表示编号为 0 的节点是根节点。 
// 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立 
// 0 <= node < n 
// 至多查询 5 * 10⁴ 次 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 二分查找 动态规划 👍 252 👎 0

import java.util.*;

// 时间复杂度：O()
// 空间复杂度：O()
//leetcode submit region begin(Prohibit modification and deletion)
class TreeAncestor {
    private final int LOG = 16;
    private int[][] ancestors;

    public TreeAncestor(int n, int[] parent) {
        // 定义ancestors[i][j]为节点i的第2^j个祖先
        ancestors = new int[n][LOG];
        for (int[] row : ancestors) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < n; i++) {
            ancestors[i][0] = parent[i];
        }

        // 为啥j循环必须在外面？？？
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                if (ancestors[i][j-1] != -1) {
                    // 当前节点的第2^j个祖先，是他的第2^j-1个祖先的第2^j-1个祖先，当第2^j祖先不存在时，-1
                    ancestors[i][j] = ancestors[ancestors[i][j-1]][j-1];
                }
            }
        }



    }
    
    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < LOG; j++) {
            if (((k >> j) & 1) != 0) {
                node = ancestors[node][j];
                if (node == -1) {
                    return -1;
                }
            }
        }
        return node;
    }


}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
//leetcode submit region end(Prohibit modification and deletion)
