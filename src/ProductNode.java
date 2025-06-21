public class ProductNode
{
    ProductInformation product;
    ProductNode left;
    ProductNode right;
    int height;

    public ProductNode(ProductInformation product)
    {
        this.product=product;
        this.left=null;
        this.right=null;
        this.height=1;
    }
}
