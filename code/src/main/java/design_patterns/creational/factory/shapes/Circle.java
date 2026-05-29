package design_patterns.creational.factory.shapes;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("A circle is drawn.");
    }
}