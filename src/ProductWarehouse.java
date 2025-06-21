import java.util.*;

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
        if(balance>1&&product.getProductId()>node.left.product.getProductId())
        {
            node.left=rotateLeft(node.left);
            return rotateRight(node);
        }
        if(balance<-1&&product.getProductId()<node.right.product.getProductId())
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

    public ProductInformation searchProduct(int productId)
    {
        ProductNode node=search(root,productId);
        if(node==null)
        {
            return null;
        }
        return node.product;
    }

    private ProductNode search(ProductNode node,int productId)
    {
        if(node==null)
        {
            return null;
        }

        if(productId==node.product.getProductId())
        {
            return node;
        }
        else if(productId < node.product.getProductId())
        {
            return search(node.left, productId);
        }
        else
        {
            return search(node.right, productId);
        }
    }

    public boolean updateProductPrice(int productId,double newPrice)
    {
        ProductNode node=search(root,productId);
        if(node==null)
        {
            return false;
        }
        node.product.setProductPrice(newPrice);
        return true;
    }

    public boolean updateProductQuantity(int productId,int newQuantity)
    {
        ProductNode node=search(root,productId);
        if(node==null)
        {
            return false;
        }
        node.product.setProductQuantity(newQuantity);
        return true;
    }

    public boolean deleteProduct(int productId)
    {
        if(search(root,productId)==null)
        {
            return false;
        }
        root=delete(root,productId);
        return true;
    }

    private ProductNode delete(ProductNode node,int productId)
    {
        if(node==null)
            return null;
        if(productId<node.product.getProductId())
        {
            node.left=delete(node.left,productId);
        }
        else if(productId>node.product.getProductId())
        {
            node.right=delete(node.right,productId);
        }
        else
        {
            if(node.left==null&&node.right==null)
            {
                return null;
            }
            else if(node.left==null)
            {
                return node.right;
            }
            else if(node.right==null)
            {
                return node.left;
            }
            else
            {
                ProductNode successor=getMinValueNode(node.right);

                node.product=successor.product;
                node.right=delete(node.right,successor.product.getProductId());
            }
        }

        node.height=1+Math.max(getHeight(node.left),getHeight(node.right));

        int balance=getBalance(node);

        if(balance>1&&getBalance(node.left)>=0)
        {
            return rotateRight(node);
        }
        if(balance>1&&getBalance(node.left)<0)
        {
            node.left=rotateLeft(node.left);
            return rotateRight(node);
        }
        if(balance<-1&&getBalance(node.right)<=0)
        {
            return rotateLeft(node);
        }
        if(balance<-1&&getBalance(node.right)>0)
        {
            node.right=rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private ProductNode getMinValueNode(ProductNode node)
    {
        ProductNode current=node;
        while(current.left!=null)
        {
            current=current.left;
        }
        return current;
    }
    public ProductNode getRoot()
    {
        return root;
    }

    //report

    public double getTotalInventoryValue()
    {
        return calculateTotalValue(root);
    }
    private double calculateTotalValue(ProductNode node)
    {
        if(node==null)
            return 0;
        double currentValue=node.product.getProductPrice()*node.product.getProductQuantity();
        return currentValue+calculateTotalValue(node.left)+calculateTotalValue(node.right);
    }

    public List<ProductInformation> getAllProducts()
    {
        List<ProductInformation> products=new ArrayList<>();
        inOrderTraversal(root,products);
        return products;
    }
    private void inOrderTraversal(ProductNode node,List<ProductInformation> products)
    {
        if(node==null)
            return;
        inOrderTraversal(node.left,products);
        products.add(node.product);
        inOrderTraversal(node.right,products);
    }

    public List<ProductInformation> getLowStockProducts(int threshold)
    {
        List<ProductInformation> lowStock=new ArrayList<>();
        findLowStock(root,threshold,lowStock);
        return lowStock;
    }
    private void findLowStock(ProductNode node,int threshold,List<ProductInformation> lowStock)
    {
        if (node==null)
            return;
        findLowStock(node.left,threshold,lowStock);
        if (node.product.getProductQuantity()<threshold)
        {
            lowStock.add(node.product);
        }
        findLowStock(node.right,threshold,lowStock);
    }

}