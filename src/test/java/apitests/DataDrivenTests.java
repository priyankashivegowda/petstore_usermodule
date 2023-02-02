package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiendpoints.Userendpoints;
import apipayload.UserPOJO;
import apiutilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userID,String username,String fname,String lname,String email,String pass,String ph)
	{
		UserPOJO userpojo=new UserPOJO();
		userpojo.setId(Integer.parseInt(userID));
		userpojo.setUsername(username);
		userpojo.setFirstName(fname);
		userpojo.setLastName(lname);
		userpojo.setEmail(email);
		userpojo.setPassword(pass);
		userpojo.setPhone(ph);
		
		Response response=Userendpoints.create_user(userpojo);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=2,dataProvider="userNames",dataProviderClass=DataProviders.class)
	public void testDeleterequest(String userName)
	{
		Response response=Userendpoints.delete_user(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
