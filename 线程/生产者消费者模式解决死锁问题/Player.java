package Thread;

public class Player implements Runnable {
    private Movie m;

    public Player(Movie m) {
        this.m = m;
    }

    public void run(){
        for(int i = 0; i < 10; i++){
            if( i % 2 == 0){
                m.play("偶数");
            } else {
                m.play("奇数");
            }
        }
    }
}
