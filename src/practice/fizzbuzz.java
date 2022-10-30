package practice;

import lab05.rowlist.List;

import java.util.ArrayList;

public class fizzbuzz {

  public static ArrayList<String> fizzBuzz(int n) {
    ArrayList<String> toReturn = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        toReturn.add("FizzBuzz");
      } else if (i % 3 == 0) {
        toReturn.add("Fizz");
      } else if (i % 5 == 0) {
        toReturn.add("Buzz");
      } else {
        toReturn.set(i, Integer.toString(i));
      }
    }

    return toReturn;
  }





  public static void main(String[] args) {
    int n = 25;

    ArrayList<String> fizzReturn = fizzBuzz(n);

    fizzReturn.toString();


  }
}
