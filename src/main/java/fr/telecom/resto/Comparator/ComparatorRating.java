package fr.telecom.resto.Comparator;

import java.util.Comparator;

import fr.telecom.resto.Model.Product;

/**
 * Created by mac on 15-4-16.
 */
public class ComparatorRating implements Comparator<Product>{
    @Override
    public int compare(Product lhs, Product rhs) {
        if(lhs.getRating()<rhs.getRating()) return -1;
        else{
            if(lhs.getRating()==rhs.getRating()) return 0;
            else return 1;
        }
    }
}
