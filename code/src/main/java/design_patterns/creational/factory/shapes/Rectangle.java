package design_patterns.creational.factory.shapes;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("A rectangle is drawn.");
    }
}