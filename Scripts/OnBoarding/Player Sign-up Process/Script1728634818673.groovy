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
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

int emailCounter
int squadCounter

String emailCounterPath = "emailCounter.txt"
String squadCounterPath = "squadCounter.txt"


def emailFile = new File(emailCounterPath)
def squadFile = new File(squadCounterPath)

if(emailFile.exists() && squadFile.exists()) {
	emailCounter = emailFile.text.toInteger()
	squadCounter = squadFile.text.toInteger()
}


squadCounter ++
emailFile.write(emailCounter.toString())
squadFile.write(squadCounter.toString())

String baseEmail = ('radosavljevic.sara+' + emailCounter) + '@gmail.com'

// Postavi API ključ
String apiKey = GlobalVariable.apiKey

// Pozovi zahtev iz Object Repository
def response = WS.sendRequest(findTestObject('Object Repository/GET_List_Players'))

// Ispisi status koda odgovora
println('Status Code: ' + response.getStatusCode())

// Ako želiš da proveriš da li je odgovor uspešan
if (response.getStatusCode() == 200) {
    println('Uspešno dobijen odgovor!')
} else {
    println('Došlo je do greške: ' + response.getResponseText())
}

def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

println('jsonResponse: ' + jsonResponse)

def player = jsonResponse.squad[0]

String playerFullName = player.name

def dob = player.dateOfBirth

// Razdvajanje imena i prezimena
def nameParts = playerFullName.split(' ')

String playerName = nameParts[0]

String playerSurname = (nameParts[(1..-1)]).join(' ')

println((((('Ime igraca: ' + playerName) + ' Prezime igraca: ') + playerSurname) + ' Datum rođenja: ') + dob)

// Razdvoj datum rođenja
def dateParts = dob.split('-' // ["1996", "04", "02"]
    )

String year = dateParts[0]

String month = dateParts[1]

String day = dateParts[2]

println('Godina ' + year)

WebUI.openBrowser('')

WebUI.navigateToUrl('https://devportal.talentsweep.com/login')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_Signup'))

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/div_I am a player'))

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_First name_first-name'), playerName)

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_Last name_last-name'), playerSurname)

// Klik na polje za datum rođenja
WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/span_Date of birth_mat-mdc-button-touch-target'))

// Čekaj da se prikaže mesec
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_TalentSweep12/span_OCT 2024'), 10)

WebUI.waitForElementClickable(findTestObject('Object Repository/Page_TalentSweep12/span_OCT 2024'), 10)

// Odaberi mesec (pretpostavljamo da koristiš mapiranje meseca)
def monthMap = [('01') : 'JAN', ('02') : 'FEB', ('03') : 'MAR', ('04') : 'APR', ('05') : 'MAY', ('06') : 'JUN', ('07') : 'JUL'
    , ('08') : 'AUG', ('09') : 'SEP', ('10') : 'OCT', ('11') : 'NOV', ('12') : 'DEC']

WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(WebUI.findWebElement(findTestObject('Object Repository/Page_TalentSweep12/span_OCT 2024_mat-mdc-button-touch-target'))))

// Definišite strelicu za menjanje godina koristeći roditeljski element ili poziciju
TestObject prevYearArrow = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(@class,'mat-mdc-button')]//span[@class='mat-mdc-button-touch-target']")



// Kreiraj novi TestObject sa dinamičkim XPath-om
TestObject dynamicYear = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'" + year + "')]")

TestObject perviousButton = findTestObject('Object Repository/Page_TalentSweep12/button_Create account_mat-calendar-previous_cd2f86')

TestObject previousYearsButton = findTestObject('Object Repository/btn_Previous_24_Years')

boolean yearFound = false
int maxAttempts = 20
int attempts = 0

while (!yearFound && attempts < maxAttempts) {
	// Proveri da li je godina vidljiva
	if(WebUI.verifyElementPresent(dynamicYear, 10, FailureHandling.OPTIONAL)) {
		yearFound = true
		WebUI.delay(1)
		WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(WebUiCommonHelper.findWebElement(dynamicYear, 10)))
	} else {
		if (WebUI.verifyElementPresent(findTestObject('Object Repository/btn_Previous_24_Years'), 10)) {
			WebUI.click(findTestObject('Object Repository/btn_Previous_24_Years'))
		} else {
			WebUI.comment('Dugme za prethodnih 24 godine nije pronađeno')
		}
	}
		
}

println('Mesec ' + month)
println('Mesec mapa ' + monthMap[month])

WebUI.comment("Mesec: " + month)
WebUI.comment("Mapirani mesec: " + monthMap[month])

TestObject dynamicMonth = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[span[text() = ' ${monthMap[month]} ']]")
	
WebUI.comment("Generisani XPath: //button[@aria-label='${monthMap[month]} ${year}']")
	
// Verifikacija i klik
if (WebUI.verifyElementPresent(dynamicMonth, 10)) {
	WebUI.click(dynamicMonth) // Pokušaj da klikneš
} else {
	WebUI.comment("Mesec nije pronađen: " + monthMap[month] + " " + year)
}


TestObject dynamicDay = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[span[text() = ' ${day} ']]")

WebUI.click(dynamicDay)

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_Email_email'), baseEmail)

WebUI.setText(findTestObject('Object Repository/Page_TalentSweep12/input_Phone_phone'), '1728257927')

WebUI.click(findTestObject('Object Repository/Page_TalentSweep12/div_Create account'))


// Ispisi status koda odgovora
println('Status Code: ' + response.getStatusCode())

// Ako želiš da proveriš da li je odgovor uspešan
if (response.getStatusCode() == 200) {
    println('Uspešno dobijen odgovor!')
} else {
    println('Došlo je do greške: ' + response.getResponseText())
}

println('jsonResponse: ' + jsonResponse)

// Pretraga za tekstom
boolean isTextPresent = WebUI.verifyTextPresent('GOOOOAL!', false)

if (isTextPresent) {
	println('Element je prisutan na stranici.')
} else {
	println('Element nije pronađen na stranici.')
}

emailCounter ++
emailFile.write(emailCounter.toString())