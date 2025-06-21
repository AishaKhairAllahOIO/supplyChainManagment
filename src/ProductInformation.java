public class ProductInformation
{
    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;

    public ProductInformation(int productId,String productName,double productPrice,int productQuantity)
    {
        if (productPrice<0) throw new IllegalArgumentException("Price cannot be negative.");
        if (productQuantity<0||productQuantity>1000)
            throw new IllegalArgumentException("Quantity must be between 0 and 1000.");

        this.productId=productId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
    }

    public int getProductId()
    {
        return productId;
    }
    public String getProductName()
    {
        return productName;
    }
    public double getProductPrice()
    {
        return productPrice;
    }
    public int getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductPrice(double productPrice)
    {
        if (productPrice<0) throw new IllegalArgumentException("Price cannot be negative.");
        this.productPrice=productPrice;
    }
    public void setProductQuantity(int productQuantity)
    {
        if(productQuantity<0||productQuantity>1000)
            throw new IllegalArgumentException("Quantity must be between 0 and 1000.");
        this.productQuantity=productQuantity;
    }

    @Override
    public String toString()
    {
        return "[ID: " + productId + "] " + productName + " | Price: " + productPrice + " | Quantity: " + productQuantity;
    }
}
