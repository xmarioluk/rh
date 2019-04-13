package tests;

import base.*;
import model.TrelloModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    /**
     * Handler to manipulate objects via Selenium WebDriver.
     */
    protected static WebDriverAPI browser;

    /**
     * Handler to manipulate objects via REST API
     */
    protected static RestAPI restApi;

    /**
     * Keeps the created objects so that they can be deleted once the tests are completed
     */
    protected static Registry suiteData;
    protected static Registry testData;

    @BeforeClass
    public static void setUp() {
        suiteData = new Registry();
        browser = new WebDriverAPI(new ChromeDriver());
        restApi = new RestAPI();
        browser.login();
    }

    @AfterClass
    public static void tearDown() {
        suiteData.cleanUp();
        browser.quit();
        restApi.quit();
    }

    @Before
    public void createTestBin() {
        testData = new Registry();
    }

    @After
    public void completeTest() {
        testData.cleanUp();
        browser.getDriver().get(Config.BASE_URL);
    }

    protected static void deleteAfterSuiteRun(TrelloModel model) {
        register(model, suiteData);
    }

    protected static void deleteAfterTestRun(TrelloModel model) {
        register(model, testData);
    }

    private static void register(TrelloModel model, Registry registry) {
        Deletable object = DeletableFactory.create(model);
        registry.add(object);
    }
}
