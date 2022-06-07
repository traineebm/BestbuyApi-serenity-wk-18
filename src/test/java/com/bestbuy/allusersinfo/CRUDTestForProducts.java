package com.bestbuy.allusersinfo;

import com.bestbuy.testbase.TestBaseProducts;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CRUDTestForProducts extends TestBaseProducts {
    static String name = "Duracell - AA 1.5V CopperTop Batteries (4-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood";
    static double price = 5.49;
    static int shipping = 0;
    static String upc = "041333415017";
    static String description = "Long lasting energy";
    static String manufacturer = "Duracell";
    static String model = "MN1500B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aa-1-5v-coppertop-batteries-4-pack/48530.p?id=1099385268988&skuId=48530&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4853/48530_sa.jpg";
    static int productID;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response =productsSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the product was added")
    @Test
    public void test002() {
        HashMap<String, Object> value = productsSteps.getProductInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        productID = (int) value.get("id");
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(productID,name,type,price,shipping,upc,description,manufacturer,model,url,image).log().all().statusCode(200);
        HashMap<String, Object> value = productsSteps.getProductInfoByName(name);
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productID).statusCode(200);
        productsSteps.getProductById(productID) .statusCode(404);
    }

}
