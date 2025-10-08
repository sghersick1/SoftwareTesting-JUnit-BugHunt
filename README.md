# SoftwareTesting-JUnit-BugHunt
### Group 07: Sam Hersick and Mushtariy Ishmukhamedova  

## 📘 Overview  
This project was developed for **CS483 (Object-Oriented Programming & Design)** Assignment 3A–3B: *“Bug Hunt via JUnit.”*  
The goal is to design a small-but-nontrivial Java program with multiple classes and realistic seeded bugs, then later identify and fix them using JUnit tests.

---

## 🧩 Project Description  
Our program simulates a **Student Check-In System for Student-Athlete Study Hours**, written in **Java 21**.  
It includes several interacting classes, clear logic, and intentional subtle bugs across different categories (boundary, state, nullability, floating-point, etc.).  

---
## Project Details
### Usage
Student-Athletes are required to spend atleast **8 hours p/week** in the study. They check-in/check-out for each session that they spend in the study. Athletes are allowed to **bank hours**, meaning they spend **>= 8 hours** in the study in 1 week. Students may use banked hours in future weeks to meet the 8 hours p/week requirement. However, athletes may only use **up to 4 banked hours** in 1 week to contribute towards the 8-hour requirement (the remaining 4 must occur during that week). Athletes may only spend up to **4 hours per day** in the study, all additional hours automatically are added to the banked hours.

### Requirements
- Students must attend study **8 hours p/week**
- Students may use up to **4 banked-hours p/week**
- All hours exceeding **4 hours in one day**, automatically contribute to banked hours (not current week hours)
- Students must **check-in/check-out** for each session (datetime)
- Students are allowed **multiple sessions per day**
- Sessions **should not** overlap (ex: 1:00pm - 2:00pm & 1:30pm - 3:00pm)
- The study closes, so sessions **cannot occur across multiple days straight** (ex: 11:00pm - 2:00am)
- Administrators can access a **progress report** demonstrating the progress of each student
- Deadlines are traditionally every **thursday at midnight** (1-week example, **Wednesday morning** of previous week - **Thursday night** of current week)

### Assumptions
- Students will be signed in/signed out in real-time. This means **no chance of overlapping sessions** by "creating a session" manually
- Administrators have a constant, prebuilt array of deadlines (every thursday for the semester)
- Student requirements are constant (Hours p/week, Max Hours p/day, Max Usable Bank Hours p/week)
- Attributes passed to Admin are valid
- Admin recalculates student progress after each session ends (chronological order)

### Out of Scope
- Holidays and shortened weeks (we will treat them as normal)

---

### Testing
### Test Cases (Recommendation)
- Ensure admin can check-in students and check-out students using a **student's ID** number.
- Ensure student cannot **check-out** before checking in
- Ensure student cannot **check-in** if already checked in
- Ensure **Banked** hours are calculated correctly
- Ensure **Weekly** hours are calculated correctly

### Additional Consideration
In the real-world, students will walk up to the study desk, and an administrator will sign them in/out in real-time. For the scope of this project there will be no "manual session creation" feature, where admin's create a new session for a student. In our software development, all sessions will be **created manually** by the programmers/testers. Because all sign in/sign out actions will be done in real time, do not worry too much about session invalid sessions (overnight sessions, multi-day sessions, etc.), instead this project focuses on the logic behind calculating the weekly hours, and banked hours, given sessions and deadlines. Make sure your testing follow's chronological order like we would see in the real world.

---

## ⚙️ How to Run  
1. Clone the repository:
   ```bash
   git clone https://github.com/sghersick1/SoftwareTesting-JUnit-BugHunt.git
   cd SoftwareTesting-JUnit-BugHunt
