package fr.telecom.resto.Comparator;

import java.util.Comparator;

import fr.telecom.resto.Model.Product;

/**
 * Created by mac on 15-4-19.
 */
public class ComparatorCalorie implements Comparator<Product>{
    @Override
    public int compare(Product lhs, Product rhs) {
        if(lhs.getCalorie()<rhs.getCalorie()) return -1;
        else{
            if(lhs.getCalorie()==rhs.getCalorie()) return 0;
            else return 1;
        }
    }
}
