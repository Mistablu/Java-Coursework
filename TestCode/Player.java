package TestCode;
public class Player {
    private double yPosition;
    private Rectangle[] arr; 
    private GameArena instance;

    public Player(double y){
		this.yPosition = y;
    }
    
	public double getYPosition()
	{
		return yPosition;
	}

    public void addTo(GameArena x) {
        arr = new Rectangle[3];
        double yCenter=yPosition-20;
        double yBottom=yPosition+20;
        double yTop=yPosition-60;
        arr[0] = new Rectangle(5, yTop, 15, 40, "Blue");
        arr[1] = new Rectangle(5, yCenter, 15, 40, "Green");
        arr[2] = new Rectangle(5, yBottom, 15, 40, "Red");
        this.instance = x;
        build();
        
    }

    private void build() {
        for (int i=0;i < 3; i++) {
            instance.addRectangle(arr[i]);

        }
    }

    public void move(double y) {
        for (int i=0;i < 3; i++) {
            instance.removeRectangle(arr[i]);
        }
        this.yPosition +=y;
        this.addTo(instance);
    }

}
