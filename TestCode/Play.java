package TestCode;
public class Play {
   public static void main(String[] args) {
       double i;
       double j;
       int accel = 1;
       double yaccel = 0.5;
       GameArena instance1 = new GameArena(1028, 512);
       Ball ball1 = new Ball(512, 256, 15, "Blue");
       instance1.addBall(ball1);
       i = ball1.getXPosition();
       j = ball1.getYPosition();
       while (true) {
        if (ball1.getXPosition()+accel==0 || ball1.getXPosition()+accel==instance1.getArenaWidth())
            accel*=-1;
        i+=accel;
        ball1.setXPosition(i);
        if (ball1.getYPosition()+yaccel==0 || ball1.getYPosition()+yaccel==instance1.getArenaHeight())
            yaccel*=-1; 
        ball1.setYPosition(j);
        j+=yaccel;
        try{Thread.sleep(5);}catch(InterruptedException e){System.out.println();}  
        }  
    }    
}
