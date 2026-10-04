function longestSubArrayWithKDistinct(nums: number[], k: number): number {
  // nums = [1, 2, 1, 2, 3], k = 2
  // 4 — [1, 2, 1, 2]

  // nums = [1, 2, 3, 2, 2], k = 2
  // 4 — [2, 3, 2, 2]

  // nums = [5, 5, 5], k = 1
  // 3

  const frequency = new Map<number, number>();

  let left = 0;
  let best = 0;

  for (let right = 0; right < nums.length; right++) {
    // get the current value of the array
    const value = nums[right];

    // expand the window
    frequency.set(value, (frequency.get(value) ?? 0) + 1);

    // check for window validity
    while (frequency.size > k) {
      const leftValue = nums[left];
      const remainingCount = frequency.get(leftValue)! - 1;

      // shrink window
      if (remainingCount == 0) {
        frequency.delete(leftValue);
      } else {
        frequency.set(leftValue, remainingCount);
      }
      left++;
    }
    best = Math.max(best, (right - left + 1));
  }
  return best;
}
