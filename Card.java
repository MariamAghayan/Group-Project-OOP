import java.util.ArrayList;

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

    public Card[] array = new Card[81];

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

    ArrayList<Card> arrayNew = new ArrayList<>();
    public ArrayList<Card> twelve(){
        int num;
        for(int i = 0; i < 12;) {
            num = (int) (Math.random() * 80);
            if (array2[num] != null) {
                arrayNew.add(i, array2[num]);
                System.out.println(arrayNew.get(i));
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

    public  Card[] array3;
    ArrayList<Card[]> objectJan;
    public ArrayList<Card[]> allSets()    {
        int j, k;
        objectJan = new ArrayList<>();
        for (int i = 0; i < arrayNew.size()-2; i++){
            for (j = i+1; j < arrayNew.size()-1; j++){
                for (k = j+1; k < arrayNew.size(); k++){
                    if (isASet(arrayNew.get(i),arrayNew.get(j),arrayNew.get(k))){
                        array3 = new Card[3];
                        array3[0] = arrayNew.get(i);
                        array3[1] = arrayNew.get(j);
                        array3[2] = arrayNew.get(k);
                        objectJan.add(array3);
                    }
                }
            }
        }
        System.out.println();

        for(int i = 0; i < objectJan.size(); i++){
                System.out.print(objectJan.get(i)[0]);
                System.out.print(objectJan.get(i)[1]);
                System.out.print(objectJan.get(i)[2]);
            System.out.println();


        }
        return objectJan;
    }
    public ArrayList<Card> addThree(){
        if (objectJan.isEmpty()){
            int num;
            for(int i = 0; i < 3;) {
                num = (int) (Math.random() * 80);
                if (array2[num] != null) {
                    arrayNew.add(arrayNew.size(),array2[num]);
                    System.out.println("added");
                    System.out.println(arrayNew.get(arrayNew.size()-1));
                    array2[num] = null;
                    i++;
                }
            }
            if (allSets().isEmpty())
                System.out.println("ended");
        }
        return arrayNew;
    }

}
