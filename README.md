# Almosafer Automation Testing Project

This project automates a functional test scenario for the [Almosafer](https://www.almosafer.com/en) website using **Java**, **Selenium WebDriver**, and **Eclipse IDE**. The test verifies core features on the homepage and hotel search functionalities in both Arabic and English languages.

> This test was built as part of a hands-on automation training project under the HTU training program.

---

## 🔍 Test Scenario Overview

The automation covers the following key actions and validations:

### 🌐 Homepage Validations

- Navigate to: `https://www.almosafer.com/en`
- Assert the default **language** is English (`EN`)
- Assert the default **currency** is Saudi Riyal (`SAR`)
- Verify that the **contact numbers** are displayed correctly
- Scroll down and **zoom in** to confirm **"Qitaf" logo** is visible in the footer
- Confirm that the **Hotels** search tab is **not selected** by default
- Validate that **Flight Departure Date** is automatically set to **today + 3 days**
- Validate that **Flight Return Date** is automatically set to **today + 10 days**

---

### 🌍 Language Switching (Randomized Logic)

- Language is changed randomly using click behavior:
  - Single click: switches to Arabic
  - Double click: switches back to English
- Assertion is made to verify the language was updated correctly

---

### 🏨 Hotel Search Flow

- Switch to the **Hotels** tab (Stay tab)
- In the **destination/location** field:
  - If the language is English → randomly choose from: `Dubai`, `Jeddah`, `Riyadh`
  - If the language is Arabic → randomly choose from: `دبي`, `جدة`, `الرياض`
- Select the **first suggestion** from the autocomplete list
- Select check-in and check-out dates 
- Choose number of guests by randomly selecting from:
  - `"1 Room, 2 Adults, 0 Children"`
  - `"1 Room, 1 Adult, 0 Children"`
- Click the **Search Hotels** button

---

### 📄 Search Results Page

- Wait until the page is fully loaded (including API responses or loading bar)
- Final assertion: Confirm that the number of **available hotels** is displayed correctly

---

## 🛠️ Tools & Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Eclipse IDE**
- **Maven** (for dependency management)

---

## 📌 Notes

- The test uses **randomization** for language toggle and guest selection to simulate real-user scenarios.
- All validations are clearly asserted to ensure functional correctness of the UI and core logic.

---

## 🧑‍💻 Author

- Dalia Al Daja  
- Created as part of a practical training at **HTU – Al Hussein Technical University**

