public class ProductWarehouse
{
    private ProductNode root;
    private static int nextId=1;

    public void addProduct(String productName,double productPrice,int productQuantity)
    {
        int productId=nextId++;
        ProductInformation product=new ProductInformation(productId,productName,productPrice,productQuantity);
        root=insert(root,product);
    }

    private ProductNode insert(ProductNode node,ProductInformation product)
    {
        if(node==null)
        {
            return new ProductNode(product);
        }

        if(product.getProductId()<node.product.getProductId())
        {
            node.left=insert(node.left,product);
        }
        else if(product.getProductId()>node.product.getProductId())
        {
            node.right=insert(node.right,product);
        }
        else
        {
            return node;
        }

        node.height=1+Math.max(getHeight(node.left),getHeight(node.right));
        int balance=getBalance(node);

        if(balance>1&&product.getProductId()<node.left.product.getProductId())
        {
            return rotateRight(node);
        }
        if(balance<-1 && product.getProductId()>node.right.product.getProductId())
        {
            return rotateLeft(node);
        }
        if (balance>1&&product.getProductId()>node.left.product.getProductId())
        {
            node.left=rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance<-1&&product.getProductId()<node.right.product.getProductId())
        {
            node.right=rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private int getHeight(ProductNode node)
    {
        if(node==null)
            return 0;
        return node.height;
    }

    private int getBalance(ProductNode node)
    {
        if(node==null)
            return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    private ProductNode rotateRight(ProductNode unbalancedNode)
    {
        ProductNode pivotNode=unbalancedNode.left;
        ProductNode tempSubtree=pivotNode.right;

        pivotNode.right=unbalancedNode;
        unbalancedNode.left=tempSubtree;

        unbalancedNode.height=1+Math.max(getHeight(unbalancedNode.left),getHeight(unbalancedNode.right));
        pivotNode.height=1+Math.max(getHeight(pivotNode.left),getHeight(pivotNode.right));

        return pivotNode;
    }

    private ProductNode rotateLeft(ProductNode unbalancedNode)
    {
        ProductNode pivotNode=unbalancedNode.right;
        ProductNode tempSubtree=pivotNode.left;

        pivotNode.left=unbalancedNode;
        unbalancedNode.right=tempSubtree;

        unbalancedNode.height=1+Math.max(getHeight(unbalancedNode.left),getHeight(unbalancedNode.right));
        pivotNode.height=1+Math.max(getHeight(pivotNode.left),getHeight(pivotNode.right));

        return pivotNode;
    }

}
