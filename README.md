# Sante Price Index (Micro-Finance) 🌾💰

**Empowering Rural Vendors with Data-Driven Pricing.**

Sante Price Index is a "Vendor’s Intelligence" tool built for small-scale vendors in weekly markets (Santes). It bridges the information gap between city Mandis and village retail points, ensuring vendors maintain fair profit margins while staying competitive.

---

## 🚀 The Vision

Small vendors often buy from city Mandis but struggle to set a "Fair Selling Price." They either lose profit by selling too cheap or lose customers by selling too expensive. Sante Price Index solves this by aggregating city Mandi prices and calculating a **Recommended Retail Price (RRP)** based on transport costs, wastage, and desired profit margins.

---

## ✨ Key Features

### 🔍 1. Price Watch
- **Description:** Real-time visibility into today's Mandi prices for essential crops (Onion, Tomato, Potato, etc.).
- **Benefit:** Vendors know exactly what the "source price" is before they start their day.

### 🧮 2. Profit Calculator (Financial Literacy)
- **Description:** A smart calculator where vendors enter their specific transport costs and wastage percentages.
- **Goal:** Teaches the difference between **Gross Sales** and **Net Profit**.
- **Math:** Implements cost-plus pricing algorithms in Kotlin to ensure sustainability.

### 📋 3. Price Board (Digital Slate)
- **Description:** A full-screen, high-contrast "Digital Slate" mode (Yellow on Black).
- **Design:** Optimized for distance reading and outdoor environments.
- **Benefit:** Instantly communicate fair prices to customers, building trust through transparency.

### 📈 4. Market Trends
- **Description:** A simulated trend analysis tool that shows if prices are likely to rise or fall.
- **Impact:** Helps vendors make better inventory decisions (buy more now or wait).

---

## 🛠️ Technical Stack

- **Platform:** Android
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Latest Material 3)
- **Architecture:** MVVM (Model-View-ViewModel) + Repository Pattern
- **State Management:** StateFlow & Flow for reactive updates
- **Theme:** Rugged Dark Market Theme (Black background, Yellow accents)

---

## 🏗️ Project Structure

```
app/
├── src/main/java/com/santepriceindex/app/
│   ├── data/          # Repository and Data Models (Clean Abstraction)
│   ├── ui/
│   │   ├── components/ # Reusable Market UI Components
│   │   ├── navigation/ # Compose Navigation Graph
│   │   ├── screens/    # Feature-specific Composables (Watch, Calc, Board, Trends)
│   │   └── theme/      # Custom Typography and Color Palette
│   ├── viewmodel/     # Business Logic and State Management
│   └── utils/         # Formatting and Calculation Helpers
└── AndroidManifest.xml # Permissions and App Configuration
```

---

## ⚙️ How to Build & Run

### Using Android Studio (Recommended)
1. Clone the repository: `git clone https://github.com/your-username/sante-price-index.git`
2. Open the project in **Android Studio (Hedgehog or newer)**.
3. Allow Gradle to sync and download dependencies.
4. Select a device/emulator and click **Run**.

### Using Command Line (Terminal)
If you want to build the project without opening the full IDE:
1. Navigate to the project root.
2. Run `./gradlew assembleDebug` to build the APK.
3. The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

---

## 🎯 Impact Goals

- **Micro-Entrepreneurship:** Turning survivalist vendors into data-driven business owners.
- **Consumer Fair-Price:** Ensuring village customers are not overcharged.
- **Financial Literacy:** Bridging the gap in basic accounting for rural populations.

---

## 📜 License
This project is part of a Micro-Finance GenAI development initiative. 

---
*Developed with ❤️ for the Sante Vendors of the future.*
<img width="1919" height="1141" alt="Screenshot 2026-05-15 230405" src="https://github.com/user-attachments/assets/363ee194-8300-42a8-a5c7-0d76b2ad33c7" />
<img width="1919" height="1140" alt="Screenshot 2026-05-15 230350" src="https://github.com/user-attachments/assets/2917ea76-cc9d-4005-9bfb-c7a0429124bd" />
<img width="1919" height="1140" alt="Screenshot 2026-05-15 230316" src="https://github.com/user-attachments/assets/19e0c671-3156-4bae-88e0-d444d8738bed" />
