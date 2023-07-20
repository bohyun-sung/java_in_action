package javaInAction.chapter1;

public class Apple {

    private Integer weight;
    private Color color;

    public Apple(Integer weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple() {

    }

    public Apple(Integer integer) {
        this.weight = integer;
    }

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void printWeight() {
        System.out.println("weight: " + getWeight());
        System.out.println();
    }

    public void printColor() {
        System.out.println("color: " + getColor());
        System.out.println();
    }
}
