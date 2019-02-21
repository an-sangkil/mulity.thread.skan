package normal.callback.callback02;

public class FirstClass {
  
  String prefix;

  public FirstClass(String prefix) {
    this.prefix = prefix;
  }
  
 
  public String addPrefix(String suffix) {
    return this.prefix + ":"+ suffix;
  }
  
  

}
