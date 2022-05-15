package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private By firstName=By.id("input-firstname");
    private By lastName=By.id("input-lastname");
    private By email=By.id("input-email");
    private By password=By.id("input-password");
    private By telephone=By.id("input-telephone");
    private By confirmpassword=By.id("input-confirm");

    private By suscribeYes= By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[1]");
    private By suscribeNo= By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[2]");

    private By agreecheckbox=By.name("agree");
    private By continueBtn=By.xpath("//input[@type='submit' and @value='Continue']");
    private By sucessMesg=By.xpath("//div[@id='content']/h1");
    private By logoutLink=By.linkText("Logout");
    private By registerLink=By.linkText("Register");

    public RegistrationPage(WebDriver driver)
    {
        this.driver=driver;
        eleUtil=new ElementUtil(driver);
    }
    public boolean accountRegistration(String firstName,String lastName,String email,String password,String telephone,String subscribe)
    {
        eleUtil.doSendKeys(this.firstName,firstName);
        eleUtil.doSendKeys(this.lastName,lastName);
        eleUtil.doSendKeys(this.email,email);
        eleUtil.doSendKeys(this.telephone,telephone);
        eleUtil.doSendKeys(this.password,password);
        eleUtil.doSendKeys(this.confirmpassword,password);

        if(subscribe.equals("yes"))
        {
            eleUtil.doClick(suscribeYes);
        }
        else
        {
            eleUtil.doClick(suscribeNo);
        }
        eleUtil.doClick(agreecheckbox);
            eleUtil.doClick(continueBtn);

        String mesg=eleUtil.waitForElementToBeVisible(sucessMesg,5,1000).getText();
        if(mesg.contains(Constants.REGISTER_SUCCESS_MESG))
        {
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);

            return true;
        }
        return  false;
    }




}
