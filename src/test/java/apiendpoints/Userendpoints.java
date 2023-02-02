package apiendpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import apipayload.UserPOJO;

public class Userendpoints {

	
	public static Response create_user(UserPOJO payload) {
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
	   return response;
	   
	}
	public static Response read_user(String userName) {
		
		Response response=given()
		.pathParam("username",userName)
		
		.when()
		.get(Routes.get_url);
		
		return response;
	}
	
     public static Response update_user(String userName,UserPOJO payload) {
    	 
    	 Response response=given()
    			 .contentType(ContentType.JSON)
    			 .accept(ContentType.JSON)
    			 .pathParam("username",userName)
    			 .body(payload)
    			 
    			 .when()
    			 .put(Routes.put_url);
    	 
    	 return response;
    	 }
     public static Response delete_user(String userName) {
    	   
    	 Response response=given()
    			 .pathParam("username",userName)
    			 
    			 .when()
    			 .delete(Routes.delete_url);
    	 
    	        return response;
     }

}
