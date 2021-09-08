package Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Basics.payload;

import static org.hamcrest.Matchers.*;

public class Step2_dynamicJson {

	@Test(dataProvider="dp")
	public void addBook(String a,String b,String c,String d)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given()
		.header("Content-Type", "application/json")
		.body(payload.addBook(a,b,c,d))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		
		String id=js.getString("ID");
		System.out.println(id);
		
		String delete=given()
				.header("Content-Type", "application/json")
				.body("{\r\n" + 
						"\"ID\" : \""+id+"\"\r\n" + 
						"} ")
				.when().post("/Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1=new JsonPath(delete);
		
		String msg=js.getString("msg"); 
		Assert.assertEquals("book is successfully deleted", msg);
		
	}
	
	@DataProvider(name="dp")
	public Object[][] getData()
	{
		return new Object[][] {{"4620 Williams Mine Road12","Maplewood12","NJ112","070401"}
		,{"1829 White Pine Lane1","Dahlgren122","VA112","2244812"}
		};
	}

}
