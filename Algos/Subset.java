import java.util.*;

public class Subset {

static List<List<Integer>> subsets(int[] nums, int i) {
  if (i == nums.length) { 
    return new LinkedList<>() {{ add(new LinkedList<>()); }};
  }
  List<List<Integer>> result = new LinkedList<>();
  subsets(nums, i + 1).stream().forEach(subresult -> {
    result.add(new LinkedList<>(subresult));
    subresult.add(nums[i]);
    result.add(subresult);
  });
  return result;
}

public static void main(String[] args) {
  int[] nums = { 3, 1, 2 };
  subsets(nums, 0).stream().forEach(System.out::println);
}


}