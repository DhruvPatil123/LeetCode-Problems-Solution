from collections import Counter


class Solution:

  def minimumPushes(self, word: str) -> int:
    # Count character frequencies
    counts = Counter(word)

    # Sort frequencies in descending order
    freqs = sorted(counts.values(), reverse=True)

    total_pushes = 0
    for i, freq in enumerate(freqs):
      # Key press multiplier increases every 8 characters
      presses = (i // 8) + 1
      total_pushes += freq * presses

    return total_pushes