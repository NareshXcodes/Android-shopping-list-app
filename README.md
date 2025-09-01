# Shopping List App 🛒

![GitHub stars](https://img.shields.io/badge/status-ready-brightgreen) ![License](https://img.shields.io/badge/license-MIT-blue) ![Kotlin](https://img.shields.io/badge/Kotlin-1.9-orange)

A modern, lightweight, and user-friendly **Shopping List App** built with **Kotlin** and **Jetpack Compose**. Designed with Material Design 3 principles for a clean, accessible UI — perfect for showcasing in your GDSC application.

---

## 🎯 Project Summary

This app helps users create and manage shopping items quickly. It focuses on a minimal yet polished user experience with core features like adding, editing, and deleting items, and works well on different screen sizes and themes (light/dark).

---

## ✨ Highlights / Features

* Add, edit, and delete shopping items
* Inline editing and quantity controls
* Material Design 3 (Material You) support
* Smooth state management using `remember` & `mutableStateOf`
* Responsive layouts for phones and tablets
* Easily themable (light/dark support)
* Simple, testable code structure to demonstrate best practices

---

## 🧭 Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Architecture:** Single-Activity (recommended), Composable components
* **Build:** Gradle / Kotlin DSL
* **Minimum SDK:** 27 (can be adjusted in `module/build.gradle.kts`)

---

## 📦 Quick Start

### Prerequisites

* Android Studio Flamingo (or newer) recommended
* Android SDK 27+
* JDK 11+

### Install & Run

1. Clone the repository

   ```bash
   git clone https://github.com/NareshXcodes/ShoppingListApp.git
   cd ShoppingListApp
   ```
2. Open the project in Android Studio.
3. Let Gradle sync and build the project.
4. Run on an emulator or a physical device (API 27+).

> Tip: If Android Studio reports missing SDK or build tools, open the SDK Manager and install the recommended components.

---

## 🛠 Project Structure

```
ShoppingListApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/naresh/shoppinglistapp/
│   │   │   ├── MainActivity.kt
│   │   │   ├── ui/             # composables and screens
│   │   │   ├── data/           # models, repositories
│   │   │   └── theme/          # Material3 theme and colors
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   ├── mipmap/
│   │   │   └── values/
│   └── build.gradle.kts
├── build.gradle.kts
└── README.md
```

---

## 🖼️ Screenshots / Preview

> Replace the images in `/screenshots` with real screenshots or GIFs before sharing.

| Home                           | Add Item                          | Edit Item                           |
| ------------------------------ | --------------------------------- | ----------------------------------- |
| ![Home](/screenshots/home.png) | ![Add](/screenshots/add_item.png) | ![Edit](/screenshots/edit_item.png) |

---

## 🧩 Usage Examples (code)

**Example: Adding a new item (Composable snippet)**

```kotlin
@Composable
fun AddItemDialog(onAdd: (ShoppingItem) -> Unit) {
    var name by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf(1) }

    AlertDialog(
        onDismissRequest = { /* close */ },
        title = { Text("Add item") },
        text = {
            Column { /* TextField for name, number selector for qty */ }
        },
        confirmButton = { Button(onClick = { onAdd(ShoppingItem(name, qty)) }) }
    )
}

---

## ✅ Best Practices & Notes

* Keep composables small and focused — one responsibility per composable.
* Use `rememberSaveable` for preserving UI state across configuration changes if needed.
* Abstract data access behind a repository interface to make testing easier.
* Follow Kotlin coding conventions and keep UI business logic outside composables.

---

## 🧪 Tests

Add unit tests for any core logic (e.g., repository operations) and UI tests for critical flows using Compose testing APIs.

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 📬 Contact

* **GitHub:** [https://github.com/NareshXcodes](https://github.com/NareshXcodes)
* **Email:** `codes.xdev@gmail.com`
* **LinkedIn:** [www.linkedin.com/in/naresh-mahapatra](www.linkedin.com/in/naresh-mahapatra)

---








   
