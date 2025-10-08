# SoftwareTesting-JUnit-BugHunt
### Group 07: Sam Hersick and Mushtariy Ishmukhamedova  

## 📘 Overview  
This project was developed for **CS483 (Object-Oriented Programming & Design)** Assignment 3A–3B: *“Bug Hunt via JUnit.”*  
The goal is to design a small-but-nontrivial Java program with multiple classes and realistic seeded bugs, then later identify and fix them using JUnit tests.

---

## 🧩 Project Description  
Our program simulates a **Student Check-In System for Student-Athlete Study Hours**, written in **Java 21**.  
It includes several interacting classes, clear logic, and intentional subtle bugs across different categories (boundary, state, nullability, floating-point, etc.).  

## Software System
Student-Athletes are required to spend atleast **8 hours p/week** in the study. They check-in/check-out for each session that they spend in the study. Athletes are allowed to **bank hours**, meaning they spend **>= 8 hours** in the study in 1 week. Students may use banked hours in future weeks to meet the 8 hours p/week requirement. However, athletes may only use **up to 4 banked hours** in 1 week to contribute towards the 8-hour requirement (the remaining 4 must occur during that week). Athletes may only spend up to **4 hours per day** in the study, all additional hours automatically are added to the banked hours.

## Requirements
- Students must meet **8 hours p/week**
- Students may use up to **4 banked-hours p/week**
- All hours exceeding **4 hours in one day**, automatically contribute to banked hours (not current week hours)
- Students must **check-in/check-out** for each session (datetime)
- Students are allowed **multiple sessions per day**
- Administrators should quickly access student-info (student name, weekly hours, banked hours, etc.)
- Administrators should quickly access who **HAS NOT met current weekly requirement**

The project includes:
- 6–12 public methods across 1–3 classes  
- Javadoc documentation for all classes and methods  
- A `main()` method to demonstrate example usage  
- A **README** and **Bug Description** file for clarity and reproducibility  

---

## ⚙️ How to Run  
1. Clone the repository:
   ```bash
   git clone https://github.com/sghersick1/SoftwareTesting-JUnit-BugHunt.git
   cd SoftwareTesting-JUnit-BugHunt
