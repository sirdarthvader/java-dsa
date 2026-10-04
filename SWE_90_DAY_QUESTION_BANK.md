# Senior SWE 90-Day Master Question Bank & Curriculum
**Target:** Senior Software Engineer (L4 / L5 scope)
**Primary Language:** TypeScript / Modern Web & Systems  
**Design Philosophy:** ADHD-Aware, Pattern-First, Zero-Fluff, Spaced Retrieval

> The primary schedule in this repository is the [24-week roadmap](docs/SENIOR_SWE_24_WEEK_ROADMAP.md).
> Use this 90-day bank as an optional condensed question reference; its daily
> cadence and targets are not a second set of requirements.
> Labels such as “Classic practice problem” and “High-Yield” are study-priority notes,
> not verified frequency claims.

---

## 🧭 The ADHD Operational Contract (Read Before Starting)

1. **The Daily Floor:** On low-energy/overwhelmed days, do **not** skip. Do the 10-minute floor: open 1 previously solved problem, state its invariant aloud, and write 1 test case. Check it off. The streak of intent is preserved.
2. **Never Restart from Day 1:** If you fall off for 2 days or 4 days, **never start over at Array Basics**. Pick up directly on the next planned problem.
3. **One Micro-Outcome per Session:** Put away your phone, set a 25-minute timer, solve **one** problem. Stop. Celebrate the closed loop.
4. **The 4-Box Checkbox System:**
   - `[S]` = **Solved** independently in plain text (no autocomplete).
   - `[E]` = **Explained Aloud** (restate $\to$ brute force $\to$ invariant $\to$ complexity).
   - `[+2d]` = **Re-solved** after 48 hours without looking at past code.
   - `[+7d]` = **Re-solved** after 7 days to lock into procedural memory.

---

## Summary of the 14 Core Patterns & Target Counts

| Phase | Days | Focus Areas | High-Yield Problems |
| :--- | :--- | :--- | :--- |
| **Phase 1** | Days 1–25 | Arrays, Two Pointers, Sliding Window, Monotonic Stacks, Binary Search | 24 Problems |
| **Phase 2** | Days 26–55 | Binary Trees, Graphs (BFS/DFS), Topo Sort, Union-Find, Heaps, Tries | 28 Problems |
| **Phase 3** | Days 56–75 | Dynamic Programming (1D/2D/State), Advanced Follow-Ups & Streaming | 22 Problems |
| **Phase 4** | Days 76–90 | Timed 45-Min Plain Text Mocks, L4/L5 System Design, Leadership STAR Matrix | 10 Mocks + 6 Designs |

---

# PHASE 1: The Linear Toolkit & Pattern Fluency (Days 1 – 25)

### Week 1: Two Pointers & Hash Invariants (Days 1–7)
*Core Invariant:* Monotonic movement reduces an $O(N^2)$ nested search to $O(N)$ by discarding impossible search spaces from either end or with fast/slow runners.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 1 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Two Sum II (Sorted Array)** | LC 167 | Opposite-end pointers; sum too small $\to$ move left; sum too large $\to$ move right. | Baseline warmth; interviewer checks defensive handling of 0 or empty array. |
| 2 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **3Sum** | LC 15 | Sort array $\to$ fix first element $\to$ two pointers for the rest; skip duplicates at all 3 positions. | High test of clean duplicate skipping without using a Set. |
| 3 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Container With Most Water** | LC 11 | Width shrinks each step; to beat current area, you *must* move the shorter pillar. | Prove why moving the taller pillar can never yield a larger area. |
| 4 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Trapping Rain Water** | LC 42 | Water trapped at index $i$ depends on `min(max_left, max_right) - height[i]`. Process from the side with the smaller max. | **Common interview topic.** Follow-up: *"What if water drains dynamically or terrain is 2D?"* |
| 5 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Remove Duplicates from Sorted Array** | LC 26 | Slow pointer holds boundary of clean array; fast pointer scans forward. | In-place mutation without extra memory allocation. |
| 6 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Subarray Sum Equals K** | LC 560 | Prefix Sum invariant: $P[j] - P[i] = K \iff P[i] = P[j] - K$. Store prefix counts in Map. | Negative numbers break sliding window; prove why HashMap prefix sum is required. |
| 7 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Insert Delete GetRandom O(1)** | LC 380 | Dense array for $O(1)$ random indexing + Map of `val -> index` for $O(1)$ lookup. Swap with last element to delete in $O(1)$. | **Classic practice problem.** Clean code test; interviewer checks for off-by-one errors during swap. |

---

### Week 2: Sliding Window & Interval Sweeps (Days 8–14)
*Core Invariant:* Expand right pointer to achieve validity; shrink left pointer until validity is restored.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 8 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Longest Substring Without Repeating Characters** | LC 3 | Dynamic window; HashMap stores latest index; jump left pointer to `last_seen + 1`. | Verify edge cases: empty string, all identical chars, spaces/symbols. |
| 9 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Minimum Window Substring** | LC 76 | Frequency deficit map; track `matched_count == required_count`; contract left while valid. | Benchmark Advanced practice problem. Do not create new strings inside loop. |
| 10 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Longest Repeating Character Replacement** | LC 424 | Window is valid if `window_size - max_frequency <= k`. | Notice why `max_frequency` does not strictly need to be decremented on shrink. |
| 11 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Merge Intervals** | LC 56 | Sort by start time; if `current.start <= last_merged.end`, merge by `max(end1, end2)`. | High frequency. Must handle adjacent intervals like `[1,4]` and `[4,5]`. |
| 12 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Insert Interval** | LC 57 | Three phases: append non-overlapping before, merge overlapping with new, append non-overlapping after. | No full re-sort allowed! Must be $O(N)$ linear pass. |
| 13 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Meeting Rooms II** | LC 253 | Event sweep-line: split into start $(+1)$ and end $(-1)$ events, or use Min-Heap of ongoing end times. | **High-value practice.** Interviewer will ask: *"What if we need to return the room IDs?"* |
| 14 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Employee Free Time** | LC 759 | Flatten/Merge all working intervals across employees $\to$ gaps between merged intervals are free time. | Min-heap over $K$ sorted employee schedules ($O(N \log K)$). |

---

### Week 3: Stacks, Monotonic Structures & Binary Search (Days 15–21)
*Core Invariant:* Monotonic stack maintains elements in sorted order; pops represent finding the "next greater/smaller" boundary. Binary search on answer space verifies a monotonic boolean predicate $f(x)$.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 15 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Valid Parentheses** | LC 20 | LIFO matching; early exit if stack empty on closing bracket or mismatch. | Clean TypeScript mapping `{ ')': '(', ... }`. |
| 16 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Daily Temperatures** | LC 739 | Decreasing monotonic stack; when current temp $>$ stack top, stack top has found its next warmer day. | Base problem for all monotonic stack logic. |
| 17 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Largest Rectangle in Histogram** | LC 84 | Width of rectangle for height $h[i]$ extends between the first smaller element on left and right. | **Advanced practice benchmark.** Be comfortable with adding sentinel zero at the end. |
| 18 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Sliding Window Maximum** | LC 239 | Monotonic decreasing Deque storing indices; front is always current window max; pop out-of-window indices from front. | $O(N)$ time. Interviewers may ask this as a streaming latency metric problem. |
| 19 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Search in Rotated Sorted Array** | LC 33 | At least one half (`[left..mid]` or `[mid..right]`) is strictly sorted. Check if target lies within the sorted half. | Strict `<` vs `<=` boundary testing. |
| 20 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Find First and Last Position in Sorted Array** | LC 34 | Binary search twice: lower-bound search (bias left) and upper-bound search (bias right). | Avoid linear scan fallback when target is found. |
| 21 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Koko Eating Bananas** | LC 875 | Answer space `[1 .. max(piles)]`; check function `canEatAll(speed)` is monotonic: `T, T, T, F, F`. | Binary search on values, not indices. |
| 22 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Capacity To Ship Packages Within D Days** | LC 1011 | Search space `[max(weights) .. sum(weights)]`; greedy packing inside check function. | Identical invariant to Koko; tests pattern transfer. |

---

### Week 4: LRU & Phase 1 Synthesis Checkpoint (Days 22–25)

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 23 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **LRU Cache** | LC 146 | Doubly Linked List (pseudo head & tail) + Map of `key -> Node`. $O(1)$ evict from tail, add to head. | **Absolute Must-Know.** Clean modularity: `removeNode()`, `addToHead()`. |
| 24 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Median of Two Sorted Arrays** | LC 4 | Binary search on partition cut of the smaller array so `leftMax <= rightMin`. | Senior SWE favorite. Edge cases with partition at index 0 or N. |

---

# PHASE 2: Advanced Topics – Graphs, Trees, Heaps & Tries (Days 26 – 55)

### Week 5: Binary Trees & Bottom-Up Recursion (Days 26–32)
*Core Invariant:* Each recursive call asks: *"What do I need from my left child, what do I need from my right child, and what do I return up to my parent?"*

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 25 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Diameter of Binary Tree** | LC 543 | Return branch height to parent; update global diameter as `left_height + right_height`. | Common trap: passing diameter up instead of height. |
| 26 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Lowest Common Ancestor of a Binary Tree** | LC 236 | If root matches $p$ or $q$, return root. If both left and right subtrees return non-null, root is the LCA. | **Common interview question.** Be prepared to handle $p$ or $q$ not existing in tree. |
| 27 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Binary Tree Maximum Path Sum** | LC 124 | Return `max(0, val + max(left, right))` to parent; update global max with `val + left + right`. | Handles negative node values gracefully by clamping subtree sums to 0. |
| 28 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Serialize and Deserialize Binary Tree** | LC 297 | Pre-order traversal with delimiter and `#` for nulls; deserialize using recursion with iterator/queue. | **Classic practice problem.** Follow-up: *"How to optimize encoding size for production?"* |
| 29 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Count Complete Tree Nodes** | LC 222 | Compare leftmost depth and rightmost depth; if equal, tree is full ($2^h - 1$); otherwise recurse. $O(\log^2 N)$. | Demonstrates ability to beat standard $O(N)$ traversal. |
| 30 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Construct Binary Tree from Preorder & Inorder** | LC 105 | Preorder gives root; Inorder splits into left and right subtrees. Use Map for $O(1)$ index lookup in inorder array. | Strict index boundaries without slicing arrays. |

---

### Week 6: Graph Traversals & Topological Sort (Days 33–39)
*Core Invariant:* BFS gives shortest path in unweighted graphs. DFS explores connected components and back-edges. Kahn's (indegree) gives dependency resolution in DAGs.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 31 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Number of Islands** | LC 200 | Grid traversal; sink visited land or track in `Set<'r,c'>`. | Write clean helper `getNeighbors(r, c)`. |
| 32 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Rotting Oranges** | LC 994 | Multi-source BFS; initialize queue with all rotten oranges; count levels until fresh count == 0. | Time-tick level traversal; interviewer checks edge case with unreachable fresh orange. |
| 33 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Word Ladder** | LC 127 | Shortest transformation path = BFS. Generate 26 wildcards per character or intermediate state map. | Optimization: Bidirectional BFS from both begin and end word. |
| 34 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Course Schedule I & II** | LC 207 / 210 | Build adjacency list + in-degree array. Queue nodes with in-degree 0 (Kahn's). If processed count $< N$, cycle exists. | Standard Interview question disguised as build pipelines or microservice startups. |
| 35 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Alien Dictionary** | LC 269 | Compare adjacent words to find first differing char $\to$ build directed graph $\to$ Topological Sort. | **Classic practice problem.** Corner case: prefix invalidity (e.g., `["abc", "ab"]` is invalid). |
| 36 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Shortest Path with Obstacles Elimination** | LC 1293 | BFS state is 3D: `(row, col, k_remaining)`. First time reaching destination is guaranteed shortest path. | Advanced interview topic; state compression in BFS. |

---

### Week 7: Disjoint Set Union (Union-Find) & Weighted Graphs (Days 40–47)
*Core Invariant:* Union-Find manages dynamic connectivity with near $O(1)$ path compression + rank. Dijkstra uses a priority queue to guarantee optimal path relaxation.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 37 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Redundant Connection** | LC 684 | Iterate edges; if `find(u) === find(v)`, edge forms a cycle and is redundant; else `union(u, v)`. | Template for standard DSU implementation. |
| 38 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Accounts Merge** | LC 721 | Map each email to account owner; connect all emails in the same account using Union-Find. | **Top Common practice topic.** Messy data handling; tests clean data normalization. |
| 39 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Evaluate Division** | LC 399 | Graph where edge weight is multiplication factor `u / v = val`. DFS or Weighted Union-Find to find path query. | Mathematical graph modeling. |
| 40 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Network Delay Time** | LC 743 | Dijkstra's Algorithm with Min-Heap. Track `min_time` array; max value in array is total delay. | $O((E + V) \log V)$ Big-O derivation test. |
| 41 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Swim in Rising Water** | LC 778 | Min-Heap priority queue exploration (modified Dijkstra) or Binary Search on answer + BFS. | Recognize that problem asks to minimize the maximum edge along a path. |

---

### Week 8: Heaps & Prefix Trees (Tries) (Days 48–55)
*Core Invariant:* Heap maintains running extrema for streaming data. Trie partitions string search space by shared prefixes.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 42 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Find Median from Data Stream** | LC 295 | Two heaps: Max-Heap for lower half, Min-Heap for upper half. Keep sizes balanced ($|S_1 - S_2| \le 1$). | **Classic practice problem.** Follow-up: *"What if 99% of numbers are in range [0..100]?"* (Bucket count). |
| 43 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Merge k Sorted Lists** | LC 23 | Min-Heap of size $K$ storing the current head of each list. $O(N \log K)$. | Alternative: Divide and Conquer merge $O(N \log K)$ with $O(1)$ space. |
| 44 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Task Scheduler** | LC 621 | Greedily schedule most frequent tasks; idle slots depend on `(max_freq - 1) * (n)`. | Math formula vs Heap simulation approach. |
| 45 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Implement Trie (Prefix Tree)** | LC 208 | Tree of nodes where each node has `children: Map<char, Node>` and `isEnd: boolean`. | Foundational building block for word autocomplete. |
| 46 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Design Add and Search Words Data Structure** | LC 211 | Trie insert; search with wildcard `.` uses recursive DFS over all children of current node. | Handling branching recursion on wildcards cleanly. |
| 47 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Word Search II** | LC 212 | Store dictionary in Trie; DFS backtrack on 2D board against Trie nodes; prune leaf nodes to avoid duplicate matches. | **Advanced practice problem.** High emphasis on tree pruning optimization. |

---

# PHASE 3: Dynamic Programming, State Space & Follow-Ups (Days 56 – 75)

### Week 9: 1D DP & Memoization Trees (Days 56–62)
*Core Invariant:* Do **not** write bottom-up tables first! Define $f(\text{state}) = \text{optimal answer for subproblem}$; draw recursion tree; identify overlapping states; attach memoization cache.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 48 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Coin Change** | LC 322 | $f(\text{rem}) = 1 + \min(f(\text{rem} - c))$; return $-1$ if impossible. Unbounded knapsack baseline. | Edge cases: amount = 0, no combination possible. |
| 49 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Longest Increasing Subsequence** | LC 300 | $O(N^2)$ DP baseline; Follow-up: $O(N \log N)$ patience sorting with binary search (`tails` array). | Interviewer *always* asks for the $O(N \log N)$ follow-up. |
| 50 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Word Break** | LC 139 | $dp[i] = \text{true}$ if any valid word ends at $i$ and $dp[i - \text{len}] == \text{true}$. | String slicing optimization with Trie or Set. |
| 51 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Decode Ways** | LC 91 | 1-digit branch (if $\ne '0'$) + 2-digit branch (if $10 \le \text{val} \le 26$). | Tricky zero handling (`"06"`, `"10"`, `"20"`). |
| 52 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **House Robber II** | LC 213 | Circular array: run House Robber I twice—once on `[0..N-2]` and once on `[1..N-1]`. | Decomposing circular dependencies into linear subproblems. |

---

### Week 10: 2D DP, Subsequences & Grid Paths (Days 63–69)
*Core Invariant:* 2D state represents positions in two strings or coordinates in a grid. Transition determines optimal choice among diagonal (match/replace), left (delete), or top (insert).

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 53 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Unique Paths II** | LC 63 | $dp[r][c] = dp[r-1][c] + dp[r][c-1]$; zero out if obstacle. | Space optimization to single 1D array. |
| 54 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Longest Common Subsequence** | LC 1143 | If $s1[i] == s2[j] \to 1 + dp[i-1][j-1]$; else $\max(dp[i-1][j], dp[i][j-1])$. | Foundation for git diffs and edit distance. |
| 55 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Edit Distance** | LC 72 | Insert, delete, replace transitions. $3$ operations $\to$ $\min$ of 3 subproblems. | **Classic practice problem.** Show recursive memoization first! |
| 56 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Partition Equal Subset Sum** | LC 416 | 0/1 Knapsack: can subset sum to `total_sum / 2`? 1D array with reverse loop. | Early exit if `total_sum % 2 != 0`. |
| 57 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Regular Expression Matching** | LC 10 | Match current char or wildcard `.` ; Handle `*` (zero occurrences or advance string). | **Advanced practice problem.** Rigorous recursion memoization. |

---

### Week 11: Real-World Interview Systems & Follow-Up Designs (Days 70–75)
*Core Invariant:* Interviewers frequently wrap data structures inside practical APIs with streaming and scale constraints.

| # | Status | Problem | LeetCode # | Core Invariant / Mental Model | Interview Note |
|---|:---:|---|:---:|---|---|
| 58 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Snapshot Array** | LC 1146 | Don't clone the full array on snapshot! Store `(snap_id, val)` list per index $\to$ Binary Search on snap_id. | **Extremely High Common practice topic.** |
| 59 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Time Based Key-Value Store** | LC 981 | Map of `key -> Array<{timestamp, value}>` $\to$ Binary Search (upper bound) to find nearest timestamp $\le t$. | Clean API design with defensive validation. |
| 60 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Design Hit Counter** | LC 362 | Circular buffer of 300 seconds storing `{timestamp, hits}`. Space is bounded $O(1)$. | Concurrency follow-up: *"How to handle thousands of requests per second across threads?"* |
| 61 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Logger Rate Limiter** | LC 359 | Clean time-window hashing with memory cleanup. | Clean production-style code test. |
| 62 | `[ ]S [ ]E [ ]+2d [ ]+7d` | **Range Module** | LC 715 | Interval tracking with add, query, remove range. Segment Tree or TreeMap representation. | L5 follow-up favorite. |

---

# PHASE 4: Full Simulation, L4/L5 Design & Leadership (Days 76 – 90)

### 10 Timed 45-Minute Plain Text Mock Sets
*Rule:* Solve in a plain text editor with zero autocomplete, zero syntax highlighting, and a timer running.

- [ ] **Mock 01:** Grid BFS with obstacle constraints + Follow-up on memory limits.
- [ ] **Mock 02:** Interval scheduling with room allocation and streaming cancellation.
- [ ] **Mock 03:** Dynamic sliding window with minimum length + Follow-up on infinite character stream.
- [ ] **Mock 04:** Binary tree path sum with negative numbers + Follow-up on cyclic graph conversion.
- [ ] **Mock 05:** Kahn's topological sort with cycle path reconstruction.
- [ ] **Mock 06:** Running median calculation with bucket-count optimization for bounded numbers.
- [ ] **Mock 07:** Monotonic stack boundary expansion + Follow-up in 2D grid.
- [ ] **Mock 08:** Weighted graph shortest path with flight stop limits.
- [ ] **Mock 09:** Memoized 2D string matching with wildcard expansion.
- [ ] **Mock 10:** Custom data structure design combining HashMap + Doubly Linked List + Binary Search.

---

### Senior System Design Curated Topics (L4 / L5 Bar)
Ground these architectures in your frontend, platform, and cloud engineering experience:

1. [ ] **Distributed Cache with Eviction & Consistency:** LRU + Redis cluster + Cache-aside vs Write-through + Cache stampede mitigation.
2. [ ] **High-Throughput Rate Limiter:** Token Bucket vs Leaky Bucket algorithms, Redis distributed locks, sliding window log vs sliding window counter.
3. [ ] **Collaborative Document Editing (shared document editor):** Operational Transformation (OT) vs CRDTs, WebSocket bidirectional synchronization, version vectors.
4. [ ] **Large File Upload & Processing Platform:** Chunked multipart upload, resumable uploads via Presigned S3/GCS URLs, asynchronous event processing.
5. [ ] **Metrics & Log Monitoring System:** High-write ingestion pipeline, Kafka buffer, time-series database (TSDB), aggregation rollups.
6. [ ] **Global CDN & Edge Delivery for Dynamic Web Apps:** Edge caching, SSR invalidation, stale-while-revalidate, DDoS mitigation at edge.

---

### Leadership & Collaboration STAR Preparation Matrix
Prepare 5 concrete, non-trivial stories from your 8+ years of engineering:

1. **Handling Ambiguity & Ill-Defined Requirements:**
   - *Example:* Leading a complex platform migration or greenfield tooling architecture where specs were evolving.
2. **Technical Conflict & Disagree and Commit:**
   - *Example:* Disagreeing with a lead or product architect on a tech choice (e.g., SSR architecture vs Client-side state), resolving it with data/benchmarks, and supporting the team outcome.
3. **Overcoming a High-Impact Outage / Technical Mistake:**
   - *Example:* A production incident or breaking SDK release; how you conducted the post-mortem without blame and added preventive guards.
4. **Mentorship & Elevating Others:**
   - *Example:* Unblocking junior/mid engineers, introducing TypeScript coding standards or testing practices across teams.
5. **Customer & User Centricity:**
   - *Example:* Refactoring a critical frontend/platform performance bottleneck that directly reduced user-perceived latency (CWV, LCP, INP).

---

## 📅 Weekly Execution Rhythm

- **Monday – Thursday (60–75 min each):**
  - 10 min: Re-solve 1 old problem (+2d or +7d box).
  - 35 min: Solve 1 new pattern problem from the table.
  - 15 min: Dry run aloud, note invariant, log any failure mode.
- **Friday (The Daily Floor / Buffer):**
  - Light day. Review the week's error log. Do the 10-minute floor if tired.
- **Saturday (90 min Deep Work):**
  - 1 Timed 45-minute plain-text mock interview.
  - 45-minute in-depth review of edge cases.
- **Sunday (45 min):**
  - System Design topic or STAR story refinement.
  - 15-minute weekly retrospective (update checkboxes, set next week's micro-outcomes).
