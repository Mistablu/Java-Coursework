package Coursework_Code;
public class CaseNoisettes {
    public static void main(String[] args) {
        Background bg = new Background();
        RedSquirrel sq = new RedSquirrel(5,270,bg.getgridButton());
        sq.moveSquirrel(true,null);
    }
}
