package Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import static org.hamcrest.Matchers.*;

public class step1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body(payload.addPlace()).when().post("/maps/api/place/add/json")
					.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
					.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println(place_id);
		
		
		String address="Solitaire Wadhwa";
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		String updateresponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1=new JsonPath(updateresponse);
		String addedAddress=js1.getString("address");
		System.out.println("----------"+addedAddress);
		Assert.assertEquals(address, addedAddress);

	}

}
