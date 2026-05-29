package design_patterns.structural.facade;

import java.util.ArrayList;
import java.util.List;


public class Bill {


    private final int id ;
    private final List<OrderItem> orderItems = new ArrayList <> ();
    private double totalPrice;


    public Bill (int id)
    {
        this.id = id;
        this.totalPrice = 0;
    }


    public int getID ()
    {
        return this.id;
    }


    public List <OrderItem> getOrderItems ()
    {
        return this.orderItems;
    }

    public void add (FoodItem it, int quant)
    {
        orderItems.add (new OrderItem(it, quant));
    }


    public void setTotal (double total)
    {
        this.totalPrice = total;
    }


    public double getTotal ()
    {
        return this.totalPrice;
    }



}
