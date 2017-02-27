package practice.ForkJoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
public class ProductListGenerator {
    public List<Product> generate(int size){
        List<Product> list = new ArrayList<>();
        for ( int i = 0; i < size; i++ ) {
            Product product = new Product();
            product.setName("Product"+i);
            product.setPrice(10);
            list.add(product);
        }
        return list;
    }
}
