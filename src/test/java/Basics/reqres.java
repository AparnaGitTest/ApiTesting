package Basics;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoForRegres.regresMain;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class reqres {
	@Test
	public void test() {
		// TODO Auto-generated method stub
		
		String resource="api/users";
		String queryParam[]= {"page","2"};
		String header[]= {"Content-Type","application/json"};
		
		RestAssured.baseURI="https://reqres.in";
		
		regresMain response=given().log().all().queryParam(queryParam[0], queryParam[1])
				
				.header(header[0],header[1]).expect().defaultParser(Parser.JSON)
				
		.when().get(resource)
		.then().log().all().assertThat().statusCode(200)
		.extract().as(regresMain.class);
		System.out.println(response.getPage());
		
		
		
	}

}
