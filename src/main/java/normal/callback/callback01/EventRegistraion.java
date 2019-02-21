package normal.callback.callback01;

public class EventRegistraion {

   public void testCallback(StringCallback callback) {
     
     callback.callBack("test callback");
     
   }
  // http://blog.saltfactory.net/implement-java-callback/
  // http://blog.saltfactory.net/implement-java-callback/ 
  // https://gogorchg.tistory.com/entry/Kotlin-%EC%BD%9C%EB%B0%B1%ED%95%A8%EC%88%98-%EA%B5%AC%ED%98%84
  public static void main(String args[]) throws Exception {
    
    
    EventRegistraion eventRegistraion = new EventRegistraion();
    
    eventRegistraion.testCallback(new StringCallback() {
      @Override
      public void callBack(String s) {
        System.out.println(s);
        
      }
    });
    
  }
}
