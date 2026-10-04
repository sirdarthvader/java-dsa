function lengthOfLongestSubstring(str: string): number {
  // abcabcbb -> incoming string

  const alphaSet = new Set();

  let left = 0;
  let best = 0;
  for (let right = 0; right < str.length; right++) {

    // get the current string character
    const value = str[right];

    // for the current value check if the set has that character
    // if it has, we have a duplicate, record the lenght without the duiplicate
    // remove the duplicate from set and then continue
    while (alphaSet.has(value)) {
      alphaSet.delete(str[left]);
      left++;
    }

    alphaSet.add(value);

    best = Math.max(best, right - left + 1);
  }

  return best;
}
