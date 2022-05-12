package set.core;

public class Player {
    private int points;
    private String name;
    public Player(){
        points = 0;
    }
    public void addPoints(int a){
        this.points+=a;
    }
    public int getPoints(){
        return points;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

}
