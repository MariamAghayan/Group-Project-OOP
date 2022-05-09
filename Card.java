package set.core;

public class Card implements Cloneable{
    enum Shape {OVAL, DIAMOND, SQUIGGLE}

    enum Color {RED, GREEN, PURPLE}

    enum Shade {BLANK, LINES, FULL}

    enum Number {ONE, TWO, THREE}

    private Shape shape;
    private Color color;
    private Shade shade;
    private Number number;
    Shape[] shapeArr = Shape.values();
    Color[] colorArr = Color.values();
    Shade[] shadeArr = Shade.values();
    Number[] numberArr = Number.values();
    public Shape getShape(){
        return this.shape;
    }
    public void setShape(Shape shape){
        this.shape = shape;
    }
    public Color getColor(){
        return this.color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public Shade getShade(){
        return this.shade;
    }
    public void setShade(Shade shade){
        this.shade = shade;
    }
    public Number getNumber(){
        return this.number;
    }
    public void setNumber(Number number){
        this.number = number;
    }

    public Card(Shape shape, Color color, Shade shade, Number number) {
        this.shape = shape;
        this.color = color;
        this.shade = shade;
        this.number = number;
    }
    public Card(Card other){
        this.shape = other.shape;
        this.color = other.color;
        this.number = other.number;
        this.shade = other.shade;
    }
    public Card() {

    }
    public String toString() {
        return "" + shape + color + shade + number;
    }

    public Card clone() throws  CloneNotSupportedException{
        return (Card) super.clone();
    }

}
