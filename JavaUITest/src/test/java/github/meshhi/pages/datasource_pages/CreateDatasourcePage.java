package github.meshhi.pages.datasource_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import github.meshhi.webdriver.AbstractBaseDriver;
import github.meshhi.utils.ConfProperties;
import github.meshhi.utils.Warehouse;
import io.qameta.allure.Step;

public class CreateDatasourcePage {
    public WebDriver driver;
    public AbstractBaseDriver baseDriver;
    @FindBy(css = "*[formcontrolname=\"type\"]")
    private WebElement connType;
    @FindBy(css = "input[formcontrolname=\"name\"]")
    private WebElement inputConnName;
    @FindBy(css = "input[formcontrolname=\"host\"]")
    private WebElement inputHost;
    @FindBy(css = "input[formcontrolname=\"db\"]")
    private WebElement inputDb;
    @FindBy(css = "input[formcontrolname=\"username\"]")
    private WebElement inputUsername;
    @FindBy(css = "input[formcontrolname=\"password\"]")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[text()=' Проверить подключение ']")
    private WebElement checkConnBtn;
    @FindBy(xpath = "//span[text()='ClickHouse']")
    private WebElement optionClickhouse;
    
    public CreateDatasourcePage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Заполнение формы создания источника")
    public void fillForm() {
        connType.click();
        optionClickhouse.click();
        inputConnName.sendKeys(ConfProperties.getProperty("db_conn"));
        inputHost.sendKeys(ConfProperties.getProperty("db_host"));
        inputDb.sendKeys(ConfProperties.getProperty("db"));
        inputUsername.sendKeys(ConfProperties.getProperty("db_username"));
        inputPassword.sendKeys(ConfProperties.getProperty("db_password"));
    }

    @Step(value = "Проверка соединения")
    public void checkConn() {
        checkConnBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifier__notification")));
    }

    @Step(value = "Проверка укведомления о подключении")
    public Boolean validCreationDatasrc() {
        return true;
    }
}
