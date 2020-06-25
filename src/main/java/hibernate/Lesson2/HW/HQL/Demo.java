package hibernate.Lesson2.HW.HQL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        productDAO.findByContainedName("car");

        System.out.println(productDAO.findById(1987));

        System.out.println(productDAO.findByName("Big toy"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByContainedName("toy"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByNameSortedAsc("Big toy2"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByNameSortedDesc("Big toy3"));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByPrice(1000, 600));

        System.out.println();
        System.out.println("==========================================");
        System.out.println();

        System.out.println(productDAO.findByPriceSortedDesc(1000, 500));

    }
}
