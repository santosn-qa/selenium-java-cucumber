    package runner;

    import io.cucumber.testng.*;
    import org.example.pages.BasePage;
    import org.testng.annotations.*;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = "org.example.stepDefinitions"
    )

    public class TestRunner extends AbstractTestNGCucumberTests {

        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() {
            testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

            // Perform browser initialization
            BasePage.initializeWebDriver();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() {
            testNGCucumberRunner.finish();

            // Perform driver cleanup
            BasePage.quitWebDriver();
        }
    }

