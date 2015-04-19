package fr.telecom.resto.Comparator;

import java.util.Comparator;

import fr.telecom.resto.Model.Product;

/**
 * Created by mac on 15-4-19.
 */
public class ComparatorName implements Comparator<Product> {
    @Override
    public int compare(Product lhs, Product rhs) {
        return lhs.getName().compareToIgnoreCase(rhs.getName());
    }
}
