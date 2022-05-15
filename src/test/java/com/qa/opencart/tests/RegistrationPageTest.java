package com.qa.opencart.tests;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationPageTest extends BaseTest{

    @BeforeClass
    public void setUpRegistration()
    {
        registrationPage = loginpage.goToRegisterPage();
    }



    @DataProvider
    public Object[][] getRegisterData()
    {
        return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
    }


    @Test(dataProvider = "getRegisterData")
    public void userRegistrationTest(String firstName,String lastName,String telephone, String password,String subscribe)
    {
        Assert.assertTrue(registrationPage.accountRegistration(firstName,lastName,getRandomEmail(),password,telephone, subscribe));
    }

    public String getRandomEmail()
    {
        Random randomgenerator=new Random();
        String email="aprilautomation"+randomgenerator.nextInt(100)+"@gmail.com";
        return email;
    }
}
