package design_patterns.creational.factory.shapes;

public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("A square is drawn.");
    }
}