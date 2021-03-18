package Coursework_Code;
public class CaseNoisettes {
    public static void main(String[] args) {
        Background bg = new Background();
        bg.createFlower(9);
        Squirrel sq = new Squirrel(5,270,bg,"Red");
        Squirrel gq = new Squirrel(10,0,bg,"Grey");
        bg.addRedSquirrel(sq);
        bg.addGreySquirrel(gq);
    }
}
