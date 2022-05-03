public class Card implements Cloneable{
    enum Shape {OVAL, DIAMOND, SQUIGGLE}

    enum Color {RED, GREEN, PURPLE}

    enum Shade {FULL, BLANK, LINES}

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
        return "[ " + shape + " " + color + " " + shade + " " + number + " ]";
    }

    public Card[] array = new Card [81];

    public Card[] array() {
        Card cardNew;
        int h=0;
        for (int i = 0; i < shapeArr.length; i++) {
            for (int j = 0; j < colorArr.length; j++) {
                for (int k = 0; k < shadeArr.length; k++) {
                    for (int a = 0; a < numberArr.length; a++) {
                        cardNew = new Card(shapeArr[i], colorArr[j], shadeArr[k], numberArr[a]);
                        array[h++] = cardNew;
                    }
                }
            }
        }
        return array;
}
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != Card.class){
            return false;
        }
        Card objectOther = (Card) obj;
        return  this.shape == objectOther.shape && this.color == objectOther.color &&
        this.shade == objectOther.shade && this.number == objectOther.number;
    }

    public Card[] twelve(){
        Card[] arrayNew = new Card[12];
        int num;
        for(int i = 0; i < arrayNew.length;) {
                num = (int) (Math.random() * 80);
            if (array2[num] != null) {
                arrayNew[i] = array2[num];
                System.out.println(arrayNew[i]);
                array2[num] = null;
                i++;
            }

        }
        return arrayNew;
    }
    public  Card[] array2 = new Card[81];
    public Card[] copy() {

    for (int i = 0; i < array2.length; i++)
        if (array[i] != null)
            array2[i] = this.array[i].clone();
    return array2;
}

    public Card clone() {
        try {
            Card copy = (Card) super.clone();
            copy.array = this.array();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public boolean isASet(Card first, Card second, Card third){
        if((first.shape == second.shape && first.shape == third.shape) ||
                first.shape != second.shape && first.shape != third.shape && second.shape != third.shape) {
            if ((first.color == second.color && first.color == third.color) ||
                    first.color != second.color && first.color != third.color && second.color != third.color) {
                if ((first.shade == second.shade && first.shade == third.shade) ||
                        first.shade != second.shade && first.shade != third.shade && second.shade != third.shade) {
                    if ((first.number == second.number && first.number == third.number) ||
                            first.number != second.number && first.number != third.number && second.number != third.number) {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

}

