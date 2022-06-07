package com.bestbuy.allusersinfo;

import com.bestbuy.testbase.TestBaseCategories;
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
public class CRUDTestForCategories extends TestBaseCategories {
    static String name = "Lego stores Pvt Ltd" + TestUtils.getRandomValue();
    static String id = "asdfg0001233" + TestUtils.getRandomValue();
    static String categoryID;

    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will create a new category")
    @Test
    public void test001() {
        ValidatableResponse response = categoriesSteps.createCategory(name, id);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the category was created")
    @Test
    public void test002() {

        HashMap<String, Object> value = categoriesSteps.getCategoryInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        categoryID = (String) value.get("id");
    }

    @Title("Update the category information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        categoriesSteps.updateCategory(name,categoryID).log().all().statusCode(200);
        HashMap<String, Object> value = categoriesSteps.getCategoryInfoByName(name);
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Delete the category and verify if the category is deleted!")
    @Test
    public void test004() {
        categoriesSteps.deleteCategory(categoryID).statusCode(200);
        categoriesSteps.getCategoryById(categoryID).statusCode(404);
    }
}
