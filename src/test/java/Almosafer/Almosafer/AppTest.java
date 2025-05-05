package Almosafer.Almosafer;

import static org.testng.Assert.assertFalse;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	String URL = "https://www.almosafer.com/en?ncr=1";

	@BeforeTest
	public void mySetup() {

		driver.get(URL);

		driver.manage().window().maximize();
	}

	// ----------------------------------------------------------------------------------------------------//

	// Default language is correct (EN)

	@Test(priority = 1, enabled = true)
	public void check_the_language() {

		System.out.println("Test 1 : Default language is correct (EN)");

		String the_language = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		System.out.println("Detected language: " + the_language);

		if (the_language.equals("en")) {
			System.out.println("✅ Default language is English.");
		} else {
			System.out.println("❌ Assertion failed! Detected language: " + the_language);
		}

		Assert.assertEquals(the_language, "en", "Default language is incorrect!");

		System.out.println("------------------------------------------------------------");
	}

	// ----------------------------------------------------------------------------------------------------//

// Default currency is correct (SAR)

	@Test(priority = 2, enabled = true)
	public void currencyBtn() throws InterruptedException {

		System.out.println("Test2 : Default currency is correct");

		WebElement currency = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG"));

		String detectedCurrency = currency.getText();
		System.out.println("Default currency is: " + detectedCurrency);

		if (detectedCurrency.equals("SAR")) {
			System.out.println("✅ Currency is correct (SAR).");
		} else {
			System.out.println("❌ Assertion failed! Detected currency: " + detectedCurrency);
		}

		Assert.assertEquals(detectedCurrency, "SAR", "Default currency is incorrect!");

		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//

// Contact numbers are correct
	@Test(priority = 3, enabled = true)
	public void phone() {

		System.out.println("Test 3 : Contact numbers are correct");

		WebElement Phone = driver.findElement(By.tagName("strong"));
		String phone_test = Phone.getText();
		if (phone_test.equals("+966554400000")) {

			System.out.println("The phone number is correct and it is  +966554400000");
		} else {
			System.out.println("The phone number does not match. The existing number is : +966554400000");
		}
		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//

	// Verify "qitaf" logo is displayed in footer

	@Test(priority = 4, enabled = true)
	public void qitaf_logo() throws InterruptedException {
		System.out.println("Test 4 :Verify qitaf logo is displayed in footer ");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// تكبير الصفحة إلى 300%
		System.out.println("Zooming the page to 300%...");
		js.executeScript("document.body.style.zoom='300%';");
		Thread.sleep(2000);

		List<WebElement> logos = driver.findElements(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));

		if (!logos.isEmpty() && logos.get(0).isDisplayed()) {

			WebElement qitafLogo = logos.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", qitafLogo);
			System.out.println(" Qitaf logo is displayed ");
		} else {
			System.out.println("Qitaf logo is not displayed");
		}

		Thread.sleep(3000);

		js.executeScript("window.scrollTo(0,0);");
		js.executeScript("document.body.style.zoom='100%';");
		Thread.sleep(1500);

		System.out.println("------------------------------------------------------------");
	}

	// Hotels search tab is NOT selected by default.

	@Test(priority = 5, enabled = true)
	public void hotelsTab() throws InterruptedException {
		System.out.println("Test 5 :Hotels search tab is NOT selected by default");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(2000);
		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String classValue = hotelsTab.getDomAttribute("class");

		Assert.assertFalse(classValue.contains("active"));
		System.out.println("Hotels tab is not selected by default (as expected)");
		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//
	// Use random method to change language (sometime keep AR, sometime change to
		// EN)

		@Test(priority = 6, enabled = true)
		public void Change_Languages_Random_Clicks() throws InterruptedException {

			System.out.println(" Test 7 : Use random method to change language (sometime keep AR, sometime change to EN)");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement langSwitch = wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='Header__LanguageSwitch']")));

			System.out.println("The site is initially in English.");

			int numberOfClicks = new Random().nextInt(2) + 1;
			System.out.println("Random number of clicks generated: " + numberOfClicks);

			for (int i = 0; i < numberOfClicks; i++) {
				WebElement button = wait.until(
						ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-testid='Header__LanguageSwitch']")));
				button.click();
				Thread.sleep(1000);
			}

			WebElement finalLangSwitch = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("a[data-testid='Header__LanguageSwitch']")));

			String finalButtonText = finalLangSwitch.getText().trim();
			String finalSiteLang;

			if (finalButtonText.equals("العربية")) {
				finalSiteLang = "English";
			} else if (finalButtonText.equals("English")) {
				finalSiteLang = "Arabic";
			} else {
				finalSiteLang = "Unknown";
			}

			System.out.println("After toggling, the site's language is now: " + finalSiteLang);
			System.out.println("Total clicks performed: " + numberOfClicks);

			String expectedLang = (numberOfClicks % 2 == 0) ? "English" : "Arabic";

			Assert.assertEquals(finalSiteLang, expectedLang,
					"The site's language did not match the expected result based on the number of clicks.");
			System.out.println("------------------------------------------------------------");

		}

		// ----------------------------------------------------------------------------------------------------//

	// Flight departure date is set to "today+3day" by default.
	// Flight return date is set to "today+10days" by default

	@Test(priority = 7, enabled = true)
	public void selectDeparturePlus3AndReturnPlus10() throws InterruptedException {

		System.out.println("Test 6 :Flight departure date is set to \"today+3day\" by default");
		System.out.println("        Flight return date is set to \"today+10days\" by default");

		LocalDate today = LocalDate.now();
		LocalDate depDate = today.plusDays(3);
		LocalDate retDate = today.plusDays(10);
		DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
		String depTestId = "FlightSearchCalendar__" + depDate.format(fmt);
		String retTestId = "FlightSearchCalendar__" + retDate.format(fmt);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement fromBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("div[data-testid='FlightSearchBox__FromDateButton']")));
		fromBtn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.DayPicker-Body")));

		WebElement depSpan = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("span[data-testid='" + depTestId + "']")));
		depSpan.click();

		WebElement toBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("div[data-testid='FlightSearchBox__ToDateButton']")));
		toBtn.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.DayPicker-Body")));

		WebElement retSpan = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("span[data-testid='" + retTestId + "']")));
		retSpan.click();

		System.out.println("✅ Selected departure: " + depDate);
		System.out.println("✅ Selected return   : " + retDate);
		System.out.println("------------------------------------------------------------");

		Thread.sleep(1500);
	}

	// ----------------------------------------------------------------------------------------------------//

	
	// Switch to hotel search tab
	@Test(priority = 8, enabled = true)

	public void Switch_to_Hotel_Tab() throws InterruptedException {
		System.out.println("Test 8 :  Switch to hotel search tab");

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement inputFieldForPlaces = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input")));

		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);

		if (currentUrl.contains("/en")) {
			String[] enCities = { "Dubai", "Jeddah", "Riyadh" };
			String city = enCities[new Random().nextInt(enCities.length)];
			inputFieldForPlaces.sendKeys(city);
			System.out.println("Typed English city: " + city);
			Thread.sleep(1000);
			inputFieldForPlaces.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		} else if (currentUrl.contains("/ar")) {
			String[] arCities = { "دبي", "جدة", "الرياض" };
			String city = arCities[new Random().nextInt(arCities.length)];
			inputFieldForPlaces.sendKeys(city);
			System.out.println("Typed Arabic city: " + city);
			Thread.sleep(1000);
			inputFieldForPlaces.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		} else {
			String[] enCities = { "Dubai", "Jeddah", "Riyadh" };
			String city = enCities[new Random().nextInt(enCities.length)];
			inputFieldForPlaces.sendKeys(city);
			System.out.println("Typed default English city: " + city);
			Thread.sleep(1000);
			inputFieldForPlaces.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		}
		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//
	// check_In_And_cheak_out_Date
	@Test(priority = 9, enabled = true)
	public void check_In_And_cheak_out_Date() throws InterruptedException

	{
		System.out.println("Test 10 : Check in and cheak out Date ");

		WebElement theDate = driver.findElement(By.cssSelector(".sc-5uo3xu-1.ckohYI"));
		theDate.click();

		LocalDate today = LocalDate.now();
		LocalDate departDate = today.plusDays(3);
		LocalDate returnDate = today.plusDays(10);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String departDateStr = departDate.format(formatter); 
		String returnDateStr = returnDate.format(formatter); 

		System.out.println("Calculated Check In Date : " + departDateStr);
		System.out.println("Calculated Check Out Date: " + returnDateStr);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		Thread.sleep(2000);

		WebElement departElement = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='HotelsSearchCalendar__" + departDateStr + "']")));
		departElement.click();

		Thread.sleep(1000);

		WebElement returnElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='HotelsSearchCalendar__" + returnDateStr + "']"))));
		returnElement.click();
		// System.out.println("Check Out Date selected: " + returnElement.getText());

		Thread.sleep(3000);
		System.out.println("------------------------------------------------------------");

	}
	// ----------------------------------------------------------------------------------------------------//

	@Test(priority = 10, enabled = true)
	public void Rooms_and_guest() {
		System.out.println("Test 9 : Randomly select Rooms and guest  ");

		WebElement mySelect = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select selectedRoom = new Select(mySelect);

		String currentUrl = driver.getCurrentUrl();
		String[] roomOptions;

		if (currentUrl.contains("/en")) {
			roomOptions = new String[] { "1 Room, 2 Adults, 0 Children", "1 Room, 1 Adult, 0 Children" };
		} else if (currentUrl.contains("/ar")) {
			roomOptions = new String[] { "غرفة واحدة، 2 بالغون، 0 أطفال", "1 غرفة، 1 بالغ، 0 أطفال" };
		} else {
			roomOptions = new String[] { "1 Room, 2 Adults, 0 Children", "1 Room, 1 Adult, 0 Children" };
		}

		Random rand = new Random();
		int randomIndex = rand.nextInt(roomOptions.length);

		selectedRoom.selectByVisibleText(roomOptions[randomIndex]);

		System.out.println("Selected room option: " + roomOptions[randomIndex]);

		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//

	// Click on search hotels button.
	@Test(priority = 11, enabled = true)
	public void searchButton() {
		System.out.println("Test 11 :Click on search hotels button.");

		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
		System.out.println("------------------------------------------------------------");

	}

	// ----------------------------------------------------------------------------------------------------//

	@Test(priority = 12, enabled = true)

	public void verifySearchResultsLoaded() {

		System.out.println("Test 12 : verify Search Results Loaded");


		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		    WebElement searchResultsCountElement = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid='srp_properties_found']"))
		    );

		    String resultsCountText = searchResultsCountElement.getText().trim();
		    System.out.println("Search results loaded: " + resultsCountText);

		    String digitsOnly = resultsCountText.replaceAll("\\D+", "");
		    int resultsCount = 0;
		    try {
		        resultsCount = Integer.parseInt(digitsOnly);
		    } catch (NumberFormatException e) {
		        Assert.fail("Failed to extract number from text: " + resultsCountText);
		    }
		    
		    Assert.assertTrue(resultsCount > 0, "Search results count is zero or extraction failed.");
		    
		    System.out.println("Extracted number of results: " + resultsCount);
		    System.out.println("------------------------------------------------------------");
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//		WebElement searchResultsCountElement = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.cssSelector("span[data-testid='srp_properties_found']")));
//
//		String resultsCountText = searchResultsCountElement.getText().trim();
//		System.out.println("Search results loaded: " + resultsCountText);
//
//		Assert.assertTrue(!resultsCountText.isEmpty() && resultsCountText.contains("stays found"),
//				"Search results count not found. The page may not have loaded correctly.");
//		System.out.println("------------------------------------------------------------");

	}
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-bar"))); // تأكد من عدم وجود شريط
//																						// التحميل
//
//	// الحصول على الرابط بعد البحث
//	String currentUrl = driver.getCurrentUrl();
//
//	// التأكد من أن الرابط يحتوي على "hotels"
//	if (currentUrl.contains("hotels")) {
//		System.out.println("✅ الصفحة تم تحميلها بنجاح والرابط يحتوي على 'hotels' !");
//	} else {
//		System.out.println("❌ هناك مشكلة، الرابط لا يحتوي على 'hotels'.");
//	}

}
