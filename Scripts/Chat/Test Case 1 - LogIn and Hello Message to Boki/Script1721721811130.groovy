import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:4200/login')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/button_Go to Chat'))

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_TalentSweep1.2_ng-untouched ng-pristi_d5bf53'), 
    'sara.radosavljevic@devellop.com')

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_TalentSweep1.2_ng-untouched ng-pristi_d5bf53'), 
    'saraRandom555!')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/button_Sign In'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/div_Boki'))

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_Chat with Boki_ng-untouched ng-pristi_2aff37'), 
    'Zdravo Boki :)')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/button_Send'))

WebUI.verifyElementText(findTestObject('Object Repository/Page_TalentSweep12/div_false'), 'false')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/button_Sign Out'))

WebUI.closeBrowser()

