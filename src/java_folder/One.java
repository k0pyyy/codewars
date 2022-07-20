package java_folder;

public class One
 {
  public static int squareSum(int[] n)
  { 
    int sum = 0;
    for (int num: n) {
      sum += Math.pow(num, 2);
    }
    return sum;
  }

  public static void main(String[] args) {
    System.out.println(squareSum(new int[] {1, 2, 2}));
  }
}