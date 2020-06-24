package jdbc.Lesson3;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();


        Product product = new Product(10, "testgdfgdfg", "test descriptiondfgdfgdfg", 9888889);
        Product product1 = new Product(11, "Maze", "Minos", 1489);

//        productDAO.save(product);

        productDAO.delete(10);

//        productDAO.delete2(10);
//
//        productDAO.update(product);



        System.out.println(productDAO.getAllProducts());

    }
}
