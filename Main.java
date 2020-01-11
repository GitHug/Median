class Main {
  public static void main(String[] args) {
    System.out.println(new Solution().median(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6} ));
    System.out.println(new Solution().median(new int[] { 1, 3, 5, 7 }, new int[] { 2, 4, 6, 7} ));
  }
}

class Solution {

  private static class Subarray {
    private int[] underlying;
    private int start;
    private int size;

    private Subarray(int[] underlying, int start, int size) {
      this.underlying = underlying;
      this.start = start;
      this.size = size;
    }

    private static Subarray fromArray(int[] arr) {
      return new Subarray(arr, 0, arr.length);
    }

    private Subarray subarray(int start, int end) {
      if (start > end) throw new IllegalArgumentException();
      if (end > size) throw new IndexOutOfBoundsException();

      return new Subarray(underlying, this.start + start, end - start);
    }

    private int getSize() {
      return size;
    }

    private int getFirst() {
      return underlying[start];
    }

    private int getLast() {
      return underlying[start + size - 1];
    }

    private double getMedian() {
      if (size == 0) return 0;

      if (size % 2 == 0) {
        return (underlying[start + size / 2 - 1] + underlying[start + size / 2]) / 2.0;
      }
      return underlying[start + size / 2];
    }
  }

  double median(int[] arr1, int[] arr2) {
    if (arr1 == null || arr2 == null || arr1.length == 0 || arr1.length != arr2.length) throw new IllegalArgumentException();

    return median(Subarray.fromArray(arr1), Subarray.fromArray(arr2));
  }

  private double median(Subarray arr1, Subarray arr2) {
    if (arr1.getSize() == 1) return (arr1.getFirst() + arr2.getFirst()) / 2.0;
    if (arr1.getSize() == 2) return (Math.max(arr1.getFirst(), arr2.getFirst()) + Math.min(arr1.getLast(), arr2.getLast())) / 2.0;

    double median1 = arr1.getMedian();
    double median2 = arr2.getMedian();

    if (median1 == median2) return median1;
    if (median1 > median2) return median(arr1.subarray(0, arr1.getSize() / 2 + 1), arr2.subarray((arr2.getSize() - 1) / 2, arr2.getSize()));

    return median(arr1.subarray((arr1.getSize() - 1) / 2, arr1.getSize()), arr2.subarray(0, arr2.getSize() / 2 + 1)); 
  }
}