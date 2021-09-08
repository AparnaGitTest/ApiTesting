package pojoTest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;

import Basics.payload;

import static org.hamcrest.Matchers.*;

public class OuthTest {

	public static void main(String[] args) {
		
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWghpWUxAdtM48GS-H7kvp7BdsrDtbGY8LfZgkfTJVcMqj-QXbWNfAUuF6uI87HvZA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		
		
			
			//   tagname[attribute='value']
			
	String accessTokenResponse=	given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
	JsonPath js=new JsonPath(accessTokenResponse);
	String accessToken=js.getString("access_token");
		
		

		
		getCourse gc=given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);
	
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses().getWebAutomation().get(0).getCourseTitle());
				
		List<api> apiCourses=gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(apiCourses.get(i).getPrice());
					}
		}	
				
		
	}

}
