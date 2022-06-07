package com.bestbuy.allusersinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {
    @Step("Creating Services with name : {0}")
    public ValidatableResponse createService(String name) {
        ServicesPojo servicesPojos = ServicesPojo.getServicesPojo(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojos)
                .when()
                .post()
                .then();
    }

    @Step("Getting single Service information with name: {0}")
    public HashMap<String, Object> getServiceInfoByName(String name) {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating Service information with name : {0}")
    public ValidatableResponse updateService(String name,int serviceID) {
        ServicesPojo servicesPojo = ServicesPojo.getServicesPojo(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceID", serviceID)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }

    @Step("Deleting Service information with CategoryID: {0}")
    public ValidatableResponse deleteService(int serviceID) {
        return SerenityRest
                .given()
                .pathParam("serviceID", serviceID)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting Service information with CategoryID: {0}")
    public ValidatableResponse getServiceByName(int serviceID) {
        return SerenityRest
                .given()
                .pathParam("serviceID", serviceID)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }
}
