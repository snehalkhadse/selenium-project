package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{


    @DataProvider
    public Object[][] loginWrongTestData()
    {
        return new Object[][]{
                {"test11@gmail.com","test134"},
        {"test1fdsf23@gmail.com","test@123"},
        {"@###test@gmail.com","test345"},
        {" ","test3456"}
        };
}
    @Test(dataProvider="loginWrongTestData")
    public void loginNegativeTest(String username,String password){
        Assert.assertFalse(loginpage.doLoginWithWrongCredentials(username,password));
    }

}
