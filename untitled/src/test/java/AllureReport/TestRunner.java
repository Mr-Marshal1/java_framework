package AllureReport;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Allure Test Suite");

        XmlTest uiTest = new XmlTest(suite);
        uiTest.setName("Allure UI Test");
        List<XmlClass> uiClasses = new ArrayList<>();
        uiClasses.add(new XmlClass("AllureReport.AllureReportUITest"));
        uiTest.setXmlClasses(uiClasses);

        XmlTest apiTest = new XmlTest(suite);
        apiTest.setName("Allure API Test");
        List<XmlClass> apiClasses = new ArrayList<>();
        apiClasses.add(new XmlClass("AllureReport.AllureReportAPI"));
        apiTest.setXmlClasses(apiClasses);

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);

        testng.run();
    }
}
