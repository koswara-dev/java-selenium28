# Test Cases: KAI Ticket Search Functionality

This document lists test cases for verifying the train ticket search functionality on the Tiket KAI mobile web application:
**URL:** [https://m.tiketkai.com/home](https://m.tiketkai.com/home)

---

## **Test Cases Table**

| Test Case ID | Test Case Name | Step Description | Input Data | Expected Result | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **TC_SCH_001** | Search with valid inputs (1 Adult) | 1. Go to Home page<br>2. Select Origin Station<br>3. Select Destination Station<br>4. Select Departure Date (Future)<br>5. Select Passenger: 1 Adult<br>6. Click Search | Origin: `Gambir (GMR)`<br>Destination: `Bandung (BD)`<br>Date: `Tomorrow`<br>Adult: `1`<br>Infant: `0` | Redirected to search results page showing available trains and ticket prices | PASS |
| **TC_SCH_002** | Search with same Origin and Destination station | 1. Go to Home page<br>2. Select Origin Station<br>3. Select same Station as Destination<br>4. Click Search | Origin: `Gambir (GMR)`<br>Destination: `Gambir (GMR)` | Validation message displayed: "Stasiun asal dan tujuan tidak boleh sama" (or search is prevented) | PASS |
| **TC_SCH_003** | Search with past Departure Date | 1. Go to Home page<br>2. Select Origin and Destination<br>3. Attempt to select a past date in calendar | Origin: `Gambir (GMR)`<br>Destination: `Bandung (BD)`<br>Date: `Yesterday` | Past dates are disabled/unclickable in the calendar picker | PASS |
| **TC_SCH_004** | Search with empty Origin Station | 1. Go to Home page<br>2. Clear/leave Origin field blank<br>3. Fill Destination and Date<br>4. Click Search | Origin: `(empty)`<br>Destination: `Bandung (BD)`<br>Date: `Tomorrow`<br>Adult: `1` | Validation message "Stasiun asal harus diisi" displayed | PASS |
| **TC_SCH_005** | Search with empty Destination Station | 1. Go to Home page<br>2. Fill Origin and Date<br>3. Clear/leave Destination field blank<br>4. Click Search | Origin: `Gambir (GMR)`<br>Destination: `(empty)`<br>Date: `Tomorrow`<br>Adult: `1` | Validation message "Stasiun tujuan harus diisi" displayed | PASS |
| **TC_SCH_006** | Search with maximum passengers (4 Adults) | 1. Go to Home page<br>2. Fill Origin, Destination, Date<br>3. Select Passenger: 4 Adults<br>4. Click Search | Origin: `Gambir (GMR)`<br>Destination: `Bandung (BD)`<br>Date: `Tomorrow`<br>Adult: `4` | Redirected to search results showing train list for 4 passengers | PASS |
| **TC_SCH_007** | Search with Infant count exceeding Adult count | 1. Go to Home page<br>2. Fill Origin, Destination, Date<br>3. Select 1 Adult passenger<br>4. Attempt to select 2 Infant passengers | Origin: `Gambir (GMR)`<br>Destination: `Bandung (BD)`<br>Date: `Tomorrow`<br>Adult: `1`<br>Infant: `2` | Validation/warning displayed: "Jumlah infant tidak boleh melebihi jumlah penumpang dewasa" (or selector prevents selection) | PASS |

---

## **Detailed Step-by-Step Scenarios**

### **TC_SCH_001: Search with valid inputs (1 Adult)**
1. **Pre-condition:** User is on the KAI Ticket Search Page.
2. **Steps:**
   1. Tap the Origin Station field and choose `Gambir`.
   2. Tap the Destination Station field and choose `Bandung`.
   3. Open the calendar and select a date in the future (e.g. tomorrow).
   4. Set the number of adult passengers to `1` and infant passengers to `0`.
   5. Tap the `Cari Tiket` / `Search` button.
3. **Expected Result:** The application redirects to the search results page showing the train schedule, train names, and departure times from Gambir to Bandung.

---

### **TC_SCH_002: Search with same Origin and Destination station**
1. **Pre-condition:** User is on the KAI Ticket Search Page.
2. **Steps:**
   1. Select `Gambir` as the Origin Station.
   2. Select `Gambir` as the Destination Station.
   3. Select a future date.
   4. Tap the `Cari Tiket` / `Search` button.
3. **Expected Result:** A validation warning appears, or the search button remains disabled to prevent searching between identical stations.

---

### **TC_SCH_007: Search with Infant count exceeding Adult count**
1. **Pre-condition:** User is on the KAI Ticket Search Page.
2. **Steps:**
   1. Select Origin `Gambir` and Destination `Bandung`.
   2. Open passenger dropdown/modal.
   3. Set Adult passenger count to `1`.
   4. Attempt to increase Infant passenger count to `2`.
3. **Expected Result:** The counter for infant passengers cannot be increased past `1` (equal to adults), or a warning is displayed stating that infant passengers must be accompanied by an adult (1 infant per 1 adult).
