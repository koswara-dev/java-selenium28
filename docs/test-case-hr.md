# Test Cases: OrangeHRM Login Functionality

This document lists test cases for verifying the login functionality on the OrangeHRM demo website:
**URL:** [https://opensource-demo.orangehrmlive.com/web/index.php/auth/login](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)

---

## **Default Demo Credentials**
* **Username:** `Admin`
* **Password:** `admin123`

---

## **Test Cases Table**

| Test Case ID | Test Case Name | Step Description | Input Data | Expected Result | Actual Result | Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **TC_LGN_001** | Login with valid credentials | 1. Go to Login page<br>2. Enter valid Username<br>3. Enter valid Password<br>4. Click Login | Username: `Admin`<br>Password: `admin123` | Successfully redirected to Dashboard page | Dashboard page displayed | PASS |
| **TC_LGN_002** | Login with invalid Username | 1. Go to Login page<br>2. Enter invalid Username<br>3. Enter valid Password<br>4. Click Login | Username: `InvalidUser`<br>Password: `admin123` | Error message "Invalid credentials" displayed | Error message "Invalid credentials" displayed | PASS |
| **TC_LGN_003** | Login with invalid Password | 1. Go to Login page<br>2. Enter valid Username<br>3. Enter invalid Password<br>4. Click Login | Username: `Admin`<br>Password: `wrongpassword` | Error message "Invalid credentials" displayed | Error message "Invalid credentials" displayed | PASS |
| **TC_LGN_004** | Login with empty Username & Password | 1. Go to Login page<br>2. Leave Username empty<br>3. Leave Password empty<br>4. Click Login | Username: `(empty)`<br>Password: `(empty)` | Validation message "Required" displayed under Username and Password fields | Validation message "Required" displayed | PASS |
| **TC_LGN_005** | Login with empty Username and valid Password | 1. Go to Login page<br>2. Leave Username empty<br>3. Enter valid Password<br>4. Click Login | Username: `(empty)`<br>Password: `admin123` | Validation message "Required" displayed under Username field | Validation message "Required" displayed | PASS |
| **TC_LGN_006** | Login with valid Username and empty Password | 1. Go to Login page<br>2. Enter valid Username<br>3. Leave Password empty<br>4. Click Login | Username: `Admin`<br>Password: `(empty)` | Validation message "Required" displayed under Password field | Validation message "Required" displayed | PASS |
| **TC_LGN_007** | Login with incorrect case for Password | 1. Go to Login page<br>2. Enter valid Username<br>3. Enter valid Password with incorrect case<br>4. Click Login | Username: `Admin`<br>Password: `ADMIN123` | Error message "Invalid credentials" displayed | Error message "Invalid credentials" displayed | PASS |

---

## **Detailed Step-by-Step Scenarios**

### **TC_LGN_001: Login with valid credentials**
1. **Pre-condition:** User is on the OrangeHRM Login Page.
2. **Steps:**
   1. Locate the Username input field and enter `Admin`.
   2. Locate the Password input field and enter `admin123`.
   3. Click the `Login` button.
3. **Expected Result:** The user is logged in successfully and redirected to `https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index`.

---

### **TC_LGN_002: Login with invalid Username**
1. **Pre-condition:** User is on the OrangeHRM Login Page.
2. **Steps:**
   1. Enter `InvalidUser` in the Username field.
   2. Enter `admin123` in the Password field.
   3. Click the `Login` button.
3. **Expected Result:** An alert box showing "Invalid credentials" is displayed. The user remains on the Login Page.

---

### **TC_LGN_003: Login with invalid Password**
1. **Pre-condition:** User is on the OrangeHRM Login Page.
2. **Steps:**
   1. Enter `Admin` in the Username field.
   2. Enter `wrongpass` in the Password field.
   3. Click the `Login` button.
3. **Expected Result:** An alert box showing "Invalid credentials" is displayed. The user remains on the Login Page.

---

### **TC_LGN_004: Login with empty fields**
1. **Pre-condition:** User is on the OrangeHRM Login Page.
2. **Steps:**
   1. Clear both Username and Password fields.
   2. Click the `Login` button.
3. **Expected Result:** Under both input fields, a red message reading "Required" is displayed. No redirection occurs.
