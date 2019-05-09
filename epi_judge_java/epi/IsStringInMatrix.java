package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
public class IsStringInMatrix {
  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                 List<Integer> pattern) {
    Set<Triple> notFound = new HashSet<>();
    for (int r = 0; r < grid.size(); r++)
      for (int c = 0; c < grid.get(0).size(); c++)
        if (found(grid, r, c, pattern, 0, notFound))
          return true;
    return false;
  }

  private static boolean found(List<List<Integer>> grid, int r, int c,
                               List<Integer> pattern, int i, Set<Triple> notFound) {
    if (i == pattern.size())
      return true;
    Triple triple = new Triple(r, c, i);
    if (r < 0 || r >= grid.size() ||
        c < 0 || c >= grid.get(0).size() ||
        notFound.contains(triple))
      return false;
    if (grid.get(r).get(c) == pattern.get(i) &&
       (found(grid, r - 1, c, pattern, i + 1, notFound) ||
        found(grid, r + 1, c, pattern, i + 1, notFound) ||
        found(grid, r, c - 1, pattern, i + 1, notFound) ||
        found(grid, r, c + 1, pattern, i + 1, notFound)))
      return true;
    notFound.add(triple);
    return false;
  }

  public static class Triple {
    int row;
    int col;
    int offset;

    public Triple(int row, int col, int offset) {
      this.row = row;
      this.col = col;
      this.offset = offset;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Triple))
        return false;
      Triple other = (Triple) obj;
      return row == other.row && col == other.col && offset == other.offset;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col, offset);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
