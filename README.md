# 🎓 Alumni Management System (Java Swing)

**Author**: Bharath L  
**Technology**: Java Swing (Desktop GUI Application)  
**License**: MIT License  
**Status**: Complete ✅

---

## 📘 Overview

The **Alumni Management System** is a desktop-based Java Swing application that allows educational institutions to manage and visualize alumni data effectively. It provides a user-friendly interface for adding alumni, viewing detailed records with profile pictures, and browsing a photo gallery.

---

## ✨ Features

- 🔐 **Login Interface** (placeholder for real authentication)
- 🧑‍🎓 **Add Alumni** with profile details and photo path
- 📋 **View Alumni** in a tabular format with profile pictures
- 🖼️ **Gallery** to showcase all stored alumni images
- 💾 Data stored in `alumni.csv`
- 📁 Image loading from `resources/images` directory
- 🖼️ Auto-scaled images for consistency
- 🖱️ Navigation between sections using a `CardLayout`

---

## 📂 Project Structure

```
AlumniManagementSystem/
├── AlumniManagementSystem.java       # Main application file
├── alumni.csv                        # Stores alumni data
└── resources/
    └── images/                       # Folder to store alumni images (JPG/PNG)
```

---

## 📦 Requirements

- Java Development Kit (JDK 8 or above)
- Java Swing (included in standard JDK)

---

## 🚀 How to Run

1. **Compile the Program**  
   Open terminal or command prompt in the project directory and run:

   ```bash
   javac AlumniManagementSystem.java
   ```

2. **Run the Application**

   ```bash
   java AlumniManagementSystem
   ```

---

## 🛠️ Usage Guide

1. Launch the app → Click **Login**
2. Dashboard opens with options:
   - **Add Alumni**: Enter name, batch, department, email, phone, job title, and image path. Click **Save**.
   - **View Alumni**: Displays all stored alumni in a table format with profile images.
   - **Gallery**: Shows all images stored inside the `resources/images/` directory.
   - **Exit**: Closes the application.

---

## 📝 Notes

- Make sure the `alumni.csv` file exists in the same directory as the `.java` file. If not, it will be created automatically upon saving data.
- Ensure that the image paths provided while adding alumni are correct and the image files exist.
- All gallery images should be placed inside the `resources/images/` folder.

---

## 📃 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---
