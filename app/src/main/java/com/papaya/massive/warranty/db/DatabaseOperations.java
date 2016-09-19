package com.papaya.massive.warranty.db;

import com.papaya.massive.warranty.models.ProductDB;

import java.util.List;

/**
 * Created by christos on 8/9/2016.
 */
public interface DatabaseOperations {
    void createTable();
    void dropTable();
    boolean insertProduct();
    List<ProductDB> getAllProducts();
    List<ProductDB> getValidProducts();
    List<ProductDB> getExpiredProducts();

}
