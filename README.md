# Almosafer_Project

## ✅ Automated Test Scenario:

1. Navigate to URL: https://www.almosafer.com/en  
2. Once the website is loaded, the following assertions are made:

- Default language is correct (EN)
- Default currency is correct (SAR)
- Contact numbers are correct
- "Qitaf" logo is displayed in the footer
- Hotels search tab is **NOT** selected by default
- Flight departure date = today + **3** days
- Return date = today + **10** days

3. Use `random` method to change language:
   - Randomly decide whether to keep language as is or switch
   - Make assertion that language updated accordingly

4. Switch to **Hotel Search Tab**

5. In location field:
   - If current language = EN → randomly enter: `Dubai`, `Jeddah`, `Riyadh`
   - If current language = AR → randomly enter: `دبي`, `جدة`, `الرياض`
   - Select first result from autocomplete list

6. Randomly select one of the following options:
   - 1 room, 2 adults, 0 children
   - 1 room, 1 adult, 0 children

7. Click on **Search Hotels** button

8. On new **Search Results Page**:
   - Wait for loading to fully complete (wait for loading bar or API to finish)
   - Perform additional assertions as needed

✅ All steps implemented using **Java + Selenium + Eclipse**
