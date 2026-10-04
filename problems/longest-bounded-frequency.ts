function longestBoundedFrequency(nums: number[], k: number): number {
  const frequency = new Map<number, number>();

  let left = 0;
  let best = 0;

  for (let right = 0; right < nums.length; right++) {
    const value = nums[right]; // this gets the current iteration item
    frequency.set(value, (frequency.get(value) ?? 0) + 1) // set the frequency Map

    while ((frequency.get(value) ?? 0) > k) {
      let leftValue = nums[left];

      //reset the value of the current left most element
      frequency.set(leftValue, (frequency.get(leftValue) ?? 0) - 1);
      left++;
    }

    // this will calculate the highest number
    best = Math.max(best, right - left + 1);
  }

  return best;
}
