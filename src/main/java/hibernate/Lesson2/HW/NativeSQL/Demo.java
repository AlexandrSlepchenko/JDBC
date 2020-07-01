package hibernate.Lesson2.HW.NativeSQL;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findByName("Big toy"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByContainedName("toy"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByPrice(1000, 600));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByNameSortedAsc("Big toy2"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByNameSortedDesc());

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByPriceSortedDesc(1000, 600));
    }
}
