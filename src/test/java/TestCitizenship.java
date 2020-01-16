import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCitizenship<i> {


    WebDriver driver;
    private WebDriverWait wait;


    @BeforeMethod
    @Parameters({"username", "password"})
    public void setup(String username, String password) {

        System.setProperty("webdriver.chrome.driver", "/Users/yavuzaydin/Documents/Libraries/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        // login info
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();

    }

    @Test(priority = 1)
    @Parameters({"citizenName"})
    public void creatingtheCitizenship(String citizenName) throws InterruptedException {

//      Clicking on Setup

        Thread.sleep(2000);

        WebElement setUpButton = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[1]"));

        wait.until(ExpectedConditions.visibilityOf(setUpButton));

        setUpButton.click();

//      Clicking on Parameter

        WebElement parameterButton = driver.findElement(By.xpath("(//span[contains(text(),'Parameters')])[1]"));
        wait.until(ExpectedConditions.visibilityOf(parameterButton));
        parameterButton.click();

//      Clicking on Citizenship

        WebElement citizenship = driver.findElement(By.xpath("//span[@class='nav-link-title ng-star-inserted'][contains(text(),'Citizenships')]"));
        citizenship.click();

//       Clicking on plus icon

        WebElement plusIcon = driver.findElement(By.cssSelector("ms-add-button[tooltip='CITIZENSHIP.TITLE.ADD']"));
        plusIcon.click();


//      Creating the Name

        WebElement name = driver.findElement(By.cssSelector("ms-text-field[formcontrolname='name']>input"));
        name.sendKeys(citizenName);

//     Click on Save Button

        WebElement saveButton = driver.findElement(By.cssSelector("ms-save-button"));
        saveButton.click();

        wait.until(ExpectedConditions.invisibilityOf(saveButton));

    }

    @Test(priority = 6)
    @Parameters("citizenName")

    public void verifyingCitizenShip(String citizenName) throws InterruptedException {

//       Clicking on Setup

        //Thread.sleep(2000);

        WebElement setUpButton = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[1]"));

        wait.until(ExpectedConditions.visibilityOf(setUpButton));

        setUpButton.click();

//      Clicking on Parameter

        WebElement parameterButton = driver.findElement(By.xpath("(//span[contains(text(),'Parameters')])[1]"));
        wait.until(ExpectedConditions.visibilityOf(parameterButton));
        parameterButton.click();

//      Clicking on Citizenship

        WebElement citizenship = driver.findElement(By.xpath("//span[@class='nav-link-title ng-star-inserted'][contains(text(),'Citizenships')]"));
        citizenship.click();

        String actual = citizenName;


        List<WebElement> namesOfCitizenship = driver.findElements(By.xpath("//tbody//tr//td[2]"));

        Assert.assertNotEquals(namesOfCitizenship, null);


        boolean found = false;
        for (int i = 0; i < namesOfCitizenship.size(); i++) {
            if (namesOfCitizenship.get(i).getText().equals(actual)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found);

    }


    @Test(priority = 2)
    @Parameters({"nameOfPosition", "shortNameOfPosition"})
    public void creatingPosition(@Optional String nameOfPosition, @Optional String shortNameOfPosition) throws InterruptedException {

//      Click on Human Resource

        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();

//      Click on setup
        WebElement setUpHuman = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[4]"));

        setUpHuman.click();

//        Click on positions

        WebElement position = driver.findElement(By.xpath("//span[contains(text(),'Positions')]"));
        position.click();

//      Click on plus icon

        WebElement plus = driver.findElement(By.cssSelector("ms-add-button[tooltip='EMPLOYEE_POSITION.TITLE.ADD']"));
        plus.click();

//      Filling out the name of the position

        Thread.sleep(2000);
        WebElement nameOfPositions = driver.findElement(By.cssSelector("ms-text-field[formcontrolname='name']>input"));

        nameOfPositions.sendKeys(nameOfPosition);

//       Filling out the shortName

        WebElement shortNameOfPositions = driver.findElement(By.cssSelector("ms-text-field[formcontrolname='shortName']>input"));
        shortNameOfPositions.sendKeys(shortNameOfPosition);

//     Click on Save Button

        WebElement saveButton = driver.findElement(By.cssSelector("mat-dialog-actions ms-save-button"));
        saveButton.click();


    }

    @Test(priority = 5)
    @Parameters({"nameOfPosition", "shortNameOfPosition"})
    public void verifyingPosition(String nameOfPosition, String shortName) throws InterruptedException {
//       Click on Human Resource

        //  Thread.sleep(2000);
        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();
//      Click on setup
        WebElement setUpHuman = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[4]"));

        setUpHuman.click();

//        Click on positions
        WebElement position = driver.findElement(By.xpath("//span[contains(text(),'Positions')]"));
        position.click();

//       Assertion
        String expectedname = nameOfPosition;

        List<WebElement> positions = driver.findElements(By.xpath("//table//tbody//tr//td[2]"));
        Assert.assertNotEquals(positions, null);
//        Assert.assertNotEquals( names.size(), 0 );

        boolean found = false;
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getText().equals(expectedname)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found);
    }


    @Test(priority = 3)
    @Parameters({"firstNameOfEmployee", "lastNameOfEmployee", "dateOfBirth", "employeeId", "citizenName", "documentNumber"})
    public void creatingTheEmployee(String firstNameOfEmployee, String lastNameOfEmployee, String dateOfBirth, String Id, String citizenName, String docNum) throws InterruptedException {

//      Click on Human Resource
        //     Thread.sleep(2000);
        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();

//      Click on Employee

        WebElement employee = driver.findElement(By.xpath("//span[contains(text(),'Employees')]"));

        employee.click();

//      click add employee

        WebElement addEmployee = driver.findElement(By.xpath("//*[@class='svg-inline--fa fa-plus fa-w-12']"));
        addEmployee.click();


//      find  first name

        WebElement employename = driver.findElement(By.cssSelector("ms-text-field[formcontrolname='firstName']>input"));
        employename.sendKeys(firstNameOfEmployee);

//      find last name

        WebElement employelastname = driver.findElement(By.cssSelector("ms-text-field[formcontrolname='lastName']>input"));
        employelastname.sendKeys(lastNameOfEmployee);

//      find date  of birth
        WebElement birth = driver.findElement(By.xpath("//input[@id='mat-input-16']"));
        birth.sendKeys(dateOfBirth);

//      click on gender

        WebElement gender = driver.findElement(By.cssSelector("mat-select[aria-label='Gender']"));
        gender.click();

//      click on Female

        WebElement female = driver.findElement(By.cssSelector("mat-option[role='option']:last-child"));

        female.click();


//      filling out the employee id


        WebElement employeeId = driver.findElement(By.xpath("//input[@formcontrolname='employeeId']"));

        employeeId.sendKeys(Id);


//      click on citizenship
        WebElement citizenShip = driver.findElement(By.cssSelector("mat-select[aria-label='Citizenship']"));

        citizenShip.click();

        List<WebElement> optionsOfCitizens = driver.findElements(By.cssSelector("mat-option[role='option']"));
        int size = optionsOfCitizens.size();

        for (int i = 0; i < size; i++) {
            String citiz = optionsOfCitizens.get(i).getText();
            if (citiz.equals(citizenName)) {
                optionsOfCitizens.get(i).click();

            }

        }

//      Click on Document Type

        WebElement documentType = driver.findElement(By.cssSelector("mat-form-field[formgroupname='documentInfo']:nth-child(1)"));
        documentType.click();

//       Click on Personal Id

        WebElement personalId = driver.findElement(By.cssSelector("div>mat-option:nth-child(1)"));

        personalId.click();

//      filling out the document number

        WebElement documentNumber = driver.findElement(By.cssSelector("input[formcontrolname='documentNumber']"));

        documentNumber.sendKeys(docNum);

//      Click on Save Button

        WebElement saveButton = driver.findElement(By.cssSelector("ms-save-button[title='GENERAL.BUTTTON.SAVE']"));

        saveButton.click();

    }

    @Test(priority = 4)
    @Parameters({"firstNameOfEmployee", "lastNameOfEmployee"})
    public void verifyingEmployee(String name, String lastName) {

//       Click on Human Resource

        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();

//      Click on Employee

        WebElement employee = driver.findElement(By.xpath("//span[contains(text(),'Employees')]"));

        employee.click();


//       Click on status

        WebElement statusBox = driver.findElement(By.cssSelector("mat-select[aria-label='Status']"));
        statusBox.click();

        WebElement status = driver.findElement(By.xpath("//span[contains(text(),'Passive')]"));

        status.click();

//       Click on search box

        WebElement searchButton = driver.findElement(By.cssSelector("ms-search-button button"));
        searchButton.click();

//       Assertion
        String expectedname = name + " " + lastName;

        List<WebElement> employees = driver.findElements(By.xpath("//table//tbody//tr//td[3]"));

        Assert.assertNotEquals(employees, null);
//        Assert.assertNotEquals( names.size(), 0 );

        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getText().equals(expectedname)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found);
    }

    @Test(priority = 7)
    @Parameters({"firstNameOfEmployee", "lastNameOfEmployee"})
       public void deletingEmployee(String name, String lastName) throws InterruptedException {

//      Click on Human Resource

        WebElement humanResource = driver.findElement(By.xpath("//span[text()='Human Resources']"));

        humanResource.click();

//      Click on Employee

        WebElement employee = driver.findElement(By.xpath("//span[contains(text(),'Employees')]"));

        employee.click();


//       Click on status

        WebElement statusBox = driver.findElement(By.cssSelector("mat-select[aria-label='Status']"));
        statusBox.click();

        WebElement status = driver.findElement(By.xpath("//span[contains(text(),'Passive')]"));

        status.click();

//       Click on search box

        WebElement searchButton = driver.findElement(By.cssSelector("ms-search-button button"));
        searchButton.click();
//      Click on delete button and yes button

        Thread.sleep(2000);
        WebElement deleteButton = driver.findElement(By.tagName("ms-delete-button"));

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        WebElement yesButton = driver.findElement(By.cssSelector("button[type='submit']"));

        yesButton.click();

    }

    @AfterClass

    public void delete() throws InterruptedException {

//      Click on delete button and yes button


        WebElement deleteButton = driver.findElement(By.tagName("ms-delete-button"));

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

        deleteButton.click();

        WebElement yesButton = driver.findElement(By.cssSelector("button[type='submit']"));

        yesButton.click();
    }


    @AfterMethod

    public void closing() {
        driver.close();
    }
}

