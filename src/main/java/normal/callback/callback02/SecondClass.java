package normal.callback.callback02;

import java.util.function.Function;

public class SecondClass {

  public String applyFunction(String name, Function<String, String> function) {
    return function.apply(name);
  }
  
  // java function point - callback
  // https://code.i-harness.com/ko-kr/q/6c53c
  public static void main(String args[]) throws Exception {

    FirstClass firstClass = new FirstClass("first");
    SecondClass secondClass = new SecondClass();
    String data = secondClass.applyFunction("second", firstClass::addPrefix);
    System.out.println(data);
  }
}
