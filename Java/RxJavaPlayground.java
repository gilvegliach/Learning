import rx.*;
import rx.observers.*;
import rx.Observable.OnSubscribe;

public class RxJavaPlayground {
    public static void main(String[] args) {
        Observable<String> o = Observable
            .create(new OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> s) {
                    SafeSubscriber<?> ss = (SafeSubscriber<?>) s;
                    System.out.println(ss.getActual());
                    s.onNext("Hi!");
                    s.onCompleted(); 
                }        
            });
        Subscriber<String> in = new Subscriber<String>() {
            @Override
            public void onNext(String item) {
                System.out.println("ITEM: " + item);
            }
            @Override
            public void onCompleted() {}
            @Override 
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        };
        System.out.println(in);
        Subscription sub = o.subscribe(in);
        System.out.println(((SafeSubscriber<?>) sub).getActual());
    }
}   
