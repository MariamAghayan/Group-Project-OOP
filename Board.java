package set.core;

import java.util.ArrayList;

public class Board extends Card implements Cloneable{

    public Board() throws CloneNotSupportedException {
        this.array();
        this.copy();
        this.twelve();
        this.allSets();
        this.addThree();
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
        return  this.getShape() == objectOther.getShape() && this.getColor() == objectOther.getColor() &&
                this.getShade() == objectOther.getShade() && this.getNumber() == objectOther.getNumber();
    }

    public ArrayList<Card> array81Copy;

    public ArrayList<Card> copy() throws CloneNotSupportedException {

        array81Copy = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            array81Copy.add(this.array[i].clone());
        }
        return array81Copy;
    }

    public Board clone() {
        try {
            Board copy = (Board) super.clone();
            copy.array = this.array();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public ArrayList<Card> arrayNew = new ArrayList<>();
    public ArrayList<Card> twelve(){
        int num;
        for(int i = 0; i < 12;) {
            num = (int) (Math.random() * (array81Copy.size()-1));
            arrayNew.add(i, array81Copy.get(num));
            System.out.println(arrayNew.get(i));
            array81Copy.remove(num);
            i++;

        }
        return array81Copy;
    }


    public boolean isASet(Card first, Card second, Card third){
        if((first.getShape() == second.getShape() && first.getShape() == third.getShape()) ||
                first.getShape() != second.getShape() && first.getShape() != third.getShape() && second.getShape() != third.getShape()) {
            if ((first.getColor() == second.getColor() && first.getColor() == third.getColor()) ||
                    first.getColor() != second.getColor() && first.getColor() != third.getColor() && second.getColor() != third.getColor()) {
                if ((first.getShade() == second.getShade() && first.getShade() == third.getShade()) ||
                        first.getShade() != second.getShade() && first.getShade() != third.getShade() && second.getShade() != third.getShade()) {
                    if ((first.getNumber() == second.getNumber() && first.getNumber() == third.getNumber()) ||
                            first.getNumber() != second.getNumber() && first.getNumber() != third.getNumber() && second.getNumber() != third.getNumber()) {
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
    public ArrayList<Card[]> objectJan;
    public ArrayList<Card[]> allSets(){
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
            System.out.print(objectJan.get(i)[0] + " ");
            System.out.print(objectJan.get(i)[1] + " ");
            System.out.print(objectJan.get(i)[2]);
            System.out.println();


        }
        return objectJan;
    }
    public ArrayList<Card> addThree(){
        if (objectJan.isEmpty()){
            int num;
            for(int i = 0; i < 3;) {
                if(array81Copy.size() >= 3){
                    num = (int) (Math.random() * (array81Copy.size()-1));
                    arrayNew.add(arrayNew.size(), array81Copy.get(num));
                    System.out.println("added");
                    System.out.println(arrayNew.get(arrayNew.size()-1));
                    array81Copy.remove(num);
                    i++;
                } else System.out.println("the game ended");
            }
            if (allSets().isEmpty())
                System.out.println("ended");
        }
        return arrayNew;
    }
}
