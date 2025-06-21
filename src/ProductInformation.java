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

}
