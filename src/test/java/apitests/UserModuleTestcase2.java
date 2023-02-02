package apitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import apiendpoints.Userendpoints2;
import apipayload.UserPOJO;
import io.restassured.response.Response;

public class UserModuleTestcase2 {
	Faker fakedata;
	UserPOJO userpojo;
	Logger logger;
	
	@BeforeClass
	public void setup()
	{
		fakedata=new Faker();
		userpojo=new UserPOJO();
		userpojo.setId(fakedata.idNumber().hashCode());
		userpojo.setUsername(fakedata.name().username());
		userpojo.setFirstName(fakedata.name().firstName());
		userpojo.setLastName(fakedata.name().lastName());
		userpojo.setEmail(fakedata.internet().emailAddress());
		userpojo.setPassword(fakedata.internet().password());
		userpojo.setPhone(fakedata.phoneNumber().cellPhone());
		
		logger= LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testcreateuser()
	{    //create_user(UserPOJO payload)
		
		logger.info("creating user");
		Response response=Userendpoints2.createUser(userpojo);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=2)
	public void testgetuser()
	{
		logger.info("getting user details");
		Response response=Userendpoints2.read_user(this.userpojo.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=3)
	public void testupdateuser()
	{
		logger.info("updating user");
		userpojo.setFirstName(fakedata.name().fullName());
		userpojo.setLastName(fakedata.name().lastName());
		userpojo.setEmail(fakedata.internet().emailAddress());
		
		Response response=Userendpoints2.update_user(this.userpojo.getUsername(),userpojo);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checkdata after updata
		Response responseafterupdate=Userendpoints2.read_user(this.userpojo.getUsername());
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void deleteuser()
	{
		logger.info("delete user");
		Response response=Userendpoints2.delete_user(this.userpojo.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	


}
