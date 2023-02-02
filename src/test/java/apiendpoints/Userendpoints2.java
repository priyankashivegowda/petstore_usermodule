package apiendpoints;

import java.util.ResourceBundle;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import apipayload.UserPOJO;
import io.restassured.response.Response;

public class Userendpoints2 {
	
	//getting urls from property file
	
	static ResourceBundle get_url()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(UserPOJO payload)
	{
		String post_url=get_url().getString("post_url");
		
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
			
		return response;
	}
    public static Response read_user(String userName) {
	
	String geturl=get_url().getString("get_url");//"get_url" in routes.properties
		
		Response response=given()
		.pathParam("username",userName)
		
		.when()
		.get(geturl);
		
		return response;
	}
    public static Response update_user(String userName,UserPOJO payload) {
    	String puturl=get_url().getString("put_url");
    	
	 Response response=given()
			 .contentType(ContentType.JSON)
			 .accept(ContentType.JSON)
			 .pathParam("username",userName)
			 .body(payload)
			 
			 .when()
			 .put(puturl);
	 
	 return response;
	 }
    public static Response delete_user(String userName) {
    	
    	String deleteurl=get_url().getString("delete_url");
    	
   	 Response response=given()
   			 .pathParam("username",userName)
   			 
   			 .when()
   			 .delete(deleteurl);
   	 
   	        return response;
    }

}
