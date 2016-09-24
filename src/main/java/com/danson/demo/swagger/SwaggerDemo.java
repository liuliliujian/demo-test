package com.danson.demo.swagger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.models.*;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;
import io.swagger.util.Json;
import io.swagger.util.Yaml;

/**
 * Created by dansonliu on 16/8/8.
 */
public class SwaggerDemo {

    public static void main(String[] args) throws JsonProcessingException {
        Swagger swagger = new Swagger();
        swagger.setInfo(createInfo());
        swagger.setHost("api.uber.com");
        swagger.setSchemes(Arrays.asList(Scheme.HTTP, Scheme.HTTPS));
        swagger.setBasePath("/v1");
        swagger.setProduces(Arrays.asList("application/json"));
        swagger.setPaths(createPaths());
        System.out.println(Json.pretty().writeValueAsString(swagger));

        System.out.println("========================================================================================");

        System.out.println(Yaml.mapper().writeValueAsString(swagger));
    }

    private static Map<String, Path> createPaths() {
        Map<String, Path> pathMap = new HashMap<String, Path>();
        Path path = new Path();
        Operation getOperation = new Operation();
        getOperation.setSummary("Product Types");
        getOperation.setTags(Arrays.asList("product"));
        getOperation.setDescription("The Products endpoint returns information about the *Uber* products "
                                    + "offered at a given location. The response includes the display name "
                                    + "and other details about each product, and lists the products in the proper display order.");
        PathParameter idParameter = new PathParameter();
        idParameter.setName("id");
        idParameter.setDescription("record id");
        idParameter.setRequired(true);
        idParameter.setType("integer");
        idParameter.setFormat("int32");
        getOperation.addParameter(idParameter);
        QueryParameter latParameter = new QueryParameter();
        latParameter.setName("latitude");
        latParameter.setDescription("Latitude component of location.");
        latParameter.setRequired(false);
        latParameter.setType("string");
        getOperation.addParameter(latParameter);
        QueryParameter lngParameter = new QueryParameter();
        lngParameter.setName("longitude");
        lngParameter.setDescription("Longitude component of location.");
        lngParameter.setRequired(true);
        lngParameter.setType("number");
        lngParameter.setFormat("double");
        getOperation.addParameter(lngParameter);
        QueryParameter locationsParameter = new QueryParameter();
        locationsParameter.setName("locations");
        locationsParameter.setDescription("location params");
        locationsParameter.setRequired(false);
        locationsParameter.setType("array");
        ObjectProperty itemsProp = new ObjectProperty();
        itemsProp
            .property(
                "product_id",
                stringProperty("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles."));
        itemsProp.property("description", stringProperty("Description of product."));
        itemsProp.property("display_name", stringProperty("Display name of product."));
        itemsProp.property("capacity", stringProperty("Capacity of product. For example, 4 people."));
        itemsProp.property("image", stringProperty("Image URL representing the product."));
        locationsParameter.setItems(itemsProp);
        getOperation.addParameter(locationsParameter);
        path.set("get", getOperation);
        pathMap.put("/products/{id}", path);
        return pathMap;
    }

    private static Property stringProperty(String desc) {
        StringProperty stringProp = new StringProperty();
        stringProp.setDescription(desc);
        return stringProp;
    }

    private static Info createInfo() {
        Info info = new Info();
        info.setTitle("Uber API");
        info.setDescription("Move your app forward with the Uber API");
        info.setVersion("1.0.0");
        return info;
    }

}
