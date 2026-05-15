# Product Requirements Document (PRD): Sante Price Index

## 1. Project Overview
**Project Name:** Sante Price Index  
**Tagline:** Empowering Rural Vendors with Data-Driven Pricing.  
**Category:** Micro-Finance / Social Impact Tech  

## 2. Problem Statement
Small-scale vendors in weekly rural markets (Santes) buy produce from city Mandis but lack a structured method to calculate their retail selling prices. 
* **The Gap:** Vendors often rely on guesswork, leading to thin margins (selling too cheap) or lost sales (selling too expensive).
* **The Risk:** Financial instability due to an inability to factor in hidden costs like transport and wastage.

## 3. Target Audience
* **Primary:** Rural market vendors and micro-entrepreneurs.
* **Secondary:** Consumers in village markets seeking fair pricing.

## 4. Proposed Solution
A "Vendor’s Intelligence" mobile application that aggregates market data and provides a transparent calculation framework to determine the **Recommended Retail Price (RRP)**.

## 5. Key Features & Functional Requirements

### 5.1. Price Watch (Mandi Data Aggregator)
* **Requirement:** Fetch and display daily prices for essential crops from major regional Mandis.
* **Mechanism:** Real-time (simulated) data flow using Kotlin StateFlow.

### 5.2. Smart Profit Calculator
* **Requirement:** Allow vendors to input variable costs:
    * Mandi Purchase Price
    * Transport & Loading Costs
    * Estimated Wastage Percentage
    * Desired Profit Margin (%)
* **Logic:** Implements a cost-plus pricing algorithm to output a sustainable retail price per KG.

### 5.3. Financial Literacy Dashboard
* **Requirement:** Explicitly display the difference between **Gross Sales** (Total Revenue) and **Net Profit** (Revenue - All Expenses).
* **Impact:** Educates vendors on sustainable business accounting.

### 5.4. Digital Price Board (Digital Slate)
* **Requirement:** A high-contrast, large-font "customer-facing" mode.
* **Design:** Yellow on Black (rugged mode) for maximum visibility in bright sunlight.

### 5.5. Trend Analysis
* **Requirement:** Visualize price movements over 7 days.
* **Impact:** Enables predictive inventory management.

## 6. Technical Stack
* **UI:** Jetpack Compose (Material 3)
* **Logic:** Kotlin Coroutines & StateFlow
* **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern
* **Web Demo:** HTML5/Tailwind (Single-file mockup for instant demo)

## 7. Success Criteria
* Accurate calculation of RRP factoring in 4+ variable cost inputs.
* High readability of the Price Board from a distance of 3-5 meters.
* Minimalist, "rugged" UI suitable for low-literacy or outdoor environments.

## 8. Impact Goals
* Improve the average net profit margin for rural vendors by 10-15%.
* Increase financial literacy among micro-entrepreneurs.
* Ensure price transparency for rural consumers.
