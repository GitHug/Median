class Main {
  public static void main(String[] args) {
    System.out.println(new Solution().median(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6} ));
    System.out.println(new Solution().median(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6, 7} ));
  }
}

class Solution {
  double median(int[] arr1, int[] arr2) {
    int[] arr3 = new int[arr1.length + arr2.length];

    int i = 0, j = 0, k = 0;

    while (i < arr1.length || j < arr2.length) {
      if (i == arr1.length) {
        arr3[k++] = arr2[j++];
      } else if (j == arr2.length) {
        arr3[k++] = arr1[i++];
      } else if (arr1[i] < arr2[j]) {
        arr3[k++] = arr1[i++];
      } else {
        arr3[k++] = arr2[j++];
      }
    }

    if (arr3.length % 2 == 0) {
      int a = arr3.length / 2;
      int b = a - 1;

      return (arr3[a] + arr3[b]) / 2.0;
    } 
    return arr3[arr3.length / 2];
  }
}