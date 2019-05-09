package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class BinomialCoefficients {
  @EpiTest(testDataFile = "binomial_coefficients.tsv")

  public static int computeBinomialCoefficient(int n, int k) {
    int[][] binomials = new int[n + 1][k + 1];
    return compute(n, k, binomials);
  }

  private static int compute(int n, int k, int[][] binomials) {
    if (k == 0 || n == k)
      return 1;
    if (binomials[n][k] == 0) {
      int without_n = compute(n - 1, k, binomials);
      int with_n = compute(n - 1, k - 1, binomials);
      binomials[n][k] = without_n + with_n;
    }
    return binomials[n][k];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
