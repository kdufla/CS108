/**
 * Created by kdufla on 5/28/17.
 */
public class Product {
    private String name;
    private String id;
    private String image;
    private double price;

    public void setName(String name){
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getImage(){
        return image;
    }

    public double getPrice(){
        return price;
    }
}
