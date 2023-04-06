package qa_cyrcle_tasks.restassured_task;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.annotations.AfterTest;

import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;


public class CreateNewUserTest {
	 int userId;
	 String url = "https://gorest.co.in/public/v2/users";
	 
  @Test 
  @SuppressWarnings("unchecked")
  public void createNewUserTest() {
      //CREATE USER-----------------------------------------------------
	  String random = RandomStringUtils.randomAlphabetic(5);
	  JSONObject object = new JSONObject();
	  object.put("name", "Alabala Lene" + random);
	  object.put("email", "alabalale" + random + "@gmai.com");
	  object.put("gender", "female");
	  object.put("status", "active");
	  Response createUserResponce =  given().headers("Authorization", "Bearer " + "ef71746e8f09e70d03d94d8c3fd923ea6bef79733704d15fd1af771bedad7aaa", "Content-Type",
              "application/json", "Accept", "application/json").body(object.toJSONString()).when().post(url).then()
			  .extract().response();
	  System.out.println(createUserResponce.asPrettyString());
	  Assert.assertEquals(201, createUserResponce.getStatusCode());
	  userId = createUserResponce.jsonPath().get("id");
	  Assert.assertNotNull(userId);	  
	
  }
  
  @Test 
  @SuppressWarnings("unchecked")
  public void createNewUserTest2() {
      //CREATE USER-----------------------------------------------------
	  String random = RandomStringUtils.randomAlphabetic(5);
	  JSONObject object = new JSONObject();
	  object.put("name", "Alabala Lene" + random);
	  object.put("email", "alabalale" + random + "@gmai.com");
	  object.put("gender", "female");
	  object.put("status", "active");
	  Response createUserResponce =  given().headers("Authorization", "Bearer " + "ef71746e8f09e70d03d94d8c3fd923ea6bef79733704d15fd1af771bedad7aaa", "Content-Type",
              "application/json", "Accept", "application/json").body(object.toJSONString()).when().post(url).then()
			  .extract().response();
	  System.out.println(createUserResponce.asPrettyString());
	  Assert.assertEquals(201, createUserResponce.getStatusCode());
	  userId = createUserResponce.jsonPath().get("id");
	  Assert.assertNotNull(userId);	  
	
  }
  
  @Test 
  @SuppressWarnings("unchecked")
  public void createNewUserTest3() {
      //CREATE USER-----------------------------------------------------
	  String random = RandomStringUtils.randomAlphabetic(5);
	  JSONObject object = new JSONObject();
	  object.put("name", "Alabala Lene" + random);
	  object.put("email", "alabalale" + random + "@gmai.com");
	  object.put("gender", "female");
	  object.put("status", "active");
	  Response createUserResponce =  given().headers("Authorization", "Bearer " + "ef71746e8f09e70d03d94d8c3fd923ea6bef79733704d15fd1af771bedad7aaa", "Content-Type",
              "application/json", "Accept", "application/json").body(object.toJSONString()).when().post(url).then()
			  .extract().response();
	  System.out.println(createUserResponce.asPrettyString());
	  Assert.assertEquals(201, createUserResponce.getStatusCode());
	  userId = createUserResponce.jsonPath().get("id");
	  Assert.assertNotNull(userId);	  
	
  }
  
  @AfterTest(alwaysRun = true)
  public void deleteUser() throws IOException {	
	 //DELETE USER
	  Response deleteUserResponce =  given().headers("Authorization", "Bearer " + "ef71746e8f09e70d03d94d8c3fd923ea6bef79733704d15fd1af771bedad7aaa", "Content-Type",
              "application/json", "Accept", "application/json").delete(url + "/" + userId).then()
			  .extract().response();
	  
	  System.out.println(deleteUserResponce.asPrettyString());
  }
  
}
