public class Card {
    enum Shape {OVAL, DIAMOND, SQUIGGLE}

    enum Color {RED, GREEN, PURPLE}

    enum Shade {FULL, BLANK, LINES}

    enum Number {ONE, TWO, THREE}

    private Shape shape;
    private Color color;
    private Shade shade;
    private Number number;
    Shape shapeArr[] = Shape.values();
    Color colorArr[] = Color.values();
    Shade shadeArr[] = Shade.values();
    Number numberArr[] = Number.values();
    public final Card[][] array = new Card[1][81];


    public Card(Shape shape, Color color, Shade shade, Number number) {
        this.shape = shape;
        this.color = color;
        this.shade = shade;
        this.number = number;
    }

    public Card() {

    }
// deep copy, random y nuyny chlni, copy constructor,
    public String toString() {
        return "[ " + shape + " " + color + " " + shade + " " + number + " ]";
    }

    public Card[][] array() {
        Card cardNew;
        int h=0;
        for (int i = 0; i < shapeArr.length; i++) {
            for (int j = 0; j < colorArr.length; j++) {
                for (int k = 0; k < shadeArr.length; k++) {
                    for (int a = 0; a < numberArr.length; a++) {
                        cardNew = new Card(shapeArr[i], colorArr[j], shadeArr[k], numberArr[a]);
                        array[0][h++] = cardNew;
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
        for(int i = 0; i < arrayNew.length; i++) {
                arrayNew[i] = (array[0][(int) (Math.random() * 80)]);
                System.out.println(arrayNew[i]);
        }
        return arrayNew;
    }
    public  Card[][] array2 = new Card[1][81];
    public  Card[][] deepCopy(){
        if (array == null) {
            return null;
        }
        for(int i = 0; i < array2.length; i++){
            for(int j = 0; j < array2[i].length; j++){
                array2[i][j] = array[i][j];
            }
        }
        return array2;
    }
}

