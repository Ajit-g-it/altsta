package com.altsta.station.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

@RestController
public class AltStaController {
	
	@Value("amazon.aws.region")
    private String amazonAwsRegion;

    @Value("amazon.aws.accessKey")
    private String amazonAwsAccessKey;

    @Value("amazon.aws.secretKey")
    private String amazonAwsSecretKey;
    
    @Value("amazon.aws.endpoint")
    private String amazonAwsEndpoint;

    @GetMapping("/alternate/stations/{station}")
    public List<String> fetchStations(@PathVariable (name = "station") String station) {
    	   
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", ""))
                .build();
        
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("ALT_STATIONS");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#nm", "name");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":city", station);

        QuerySpec querySpec = new QuerySpec()
        	.withKeyConditionExpression("#nm = :city").withNameMap(nameMap)
            .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        List<String> itemList = new ArrayList<>();
        try {
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                itemList.addAll(item.getList("stations"));
                System.out.println(item.getList("stations") + ": " + item.getString("name"));
            }

        }
        catch (Exception e) {
        	e.printStackTrace();
            System.err.println("Unable to query movies from 1985");
            System.err.println(e.getMessage());
        }
        
//        ScanSpec scanSpec = new ScanSpec();
//
//      //  { Item: {year=1989, name=Bill & Ted's Excellent Adventure, rating=****, fans=[James, Sara]} }
//        List<Movie> itemList = new ArrayList<>();
//        try {
//            ItemCollection<ScanOutcome> items = table.scan(scanSpec);
//
//            Iterator<Item> iter = items.iterator();
//            while (iter.hasNext()) {
//                Item item = iter.next();
//                
//                System.out.println(item);
//  
//               
//               itemList.add(Movie.builder().name(item.getString("name")).year(item.getString("year")).rating(item.getString("rating")).fans( item.getList("fans")).build());
//                	
//               
//            }
//
//        } catch (Exception e) {
//        }
    	
        return itemList;
    }
}

