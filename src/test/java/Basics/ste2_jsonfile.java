package Basics;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Basics.payload;

import static org.hamcrest.Matchers.*;


public class ste2_jsonfile {
	@Test(dataProvider="dp")
	public void addBook(String a) throws IOException
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given()
		.header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("D:\\Selenium\\RestAPI\\JsonFiles\\"+a))))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		
		String id=js.getString("ID");
		System.out.println("-----------------"+id);
		
		
		
	}
	
	@DataProvider(name="dp")
	public Object[][] getData()
	{
		return new Object[][] {{"file1.json"}
		,{"file2.json"}
		};
	}

}



