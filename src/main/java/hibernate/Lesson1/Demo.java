package hibernate.Lesson1;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();

        product.setId(456);
        product.setName("TEST_Product_1");
        product.setDescription("Test_Description_Some TEXT");
        product.setPrice(999888);

//        productDAO.save(product);

//        productDAO.update(product);
        
        productDAO.delete(456);


    }
}
