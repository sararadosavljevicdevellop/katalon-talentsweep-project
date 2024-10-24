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

WebUI.navigateToUrl('https://devportal.talentsweep.com/login')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_Signup'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/div_I am a player'))

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_First name_first-name'), 'Linda')

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_Last name_last-name'), 'Caicedo')


//DOB

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_Date of birth_mat-mdc-button-touch-target'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_OCT 2024_mat-mdc-button-touch-target'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/button_Create account_mat-calendar-previous_cd2f86'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_2005'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_FEB'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_22'))

//DOB

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/input_Email_email'))

WebUI.closeBrowser()

