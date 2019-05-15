# MathSmart
**Project for - COE420 - Software Engineering**  
**Project Duration - September-December 2018**  
**Collaborators - [Amro Jundi](https://github.com/AmroAlJundi), [Bashir Jerrah](https://github.com/bashir-j)**  

Mathsmart is an application that is focused on smart schools. It provides students online maths assignments issues and uses machine learning to understand the mistakes a student makes and gives them more problems to solve in their area of weakness to improve skills. The goal of the application is to focus and improve each individual skills of each students. It offers a personal user experience by providing constantly updated student statistics to students and instructors.  
The application, by providing questions tailored to each student’s understanding of a topic, ensures that the student has an incentive to complete his/her work, and gets a more personalized education experience to improve at his or her own time and pace.

##### The application uses Google Firebase tools for different modules of the application
* [FirebaseAuth](https://firebase.google.com/products/auth/) - For registration and authentication of users
* [Firebase Realtime Database](https://firebase.google.com/products/realtime-database/) - A realtime database to store user information and information about bills, updated within the application in real-time

## Functions
* Register and login as any user into one of two dashboards - Teacher or Student  
##### Teacher  
* Create a batch of questions for any topic
* Create assignments for any topic for the students
* View scores of students
* View statistics for the status of students in particular courses/topics
##### Student
* View assignments to solve
* Solve assignments before the required deadline
* Get new questions for assignments dynamically, using machine learning algorithms (*Multivariate Linear Regression*) to determine the difficulty of the question student should receive
* View analytics for the particular student account  
## Requirements
* [Java 8](https://www.java.com/en/download/)
* [Android Studio](https://developer.android.com/studio)

## Installation
1. Clone the repo by using the following command
``` bash
$ git clone https://github.com/hussu97/MathSmart.git
$ cd MathSmart
```
2. Open the project in your Android Studio IDE
3. Go to Build->Build Bundle(s)/APK(s) -> Build APK(s) to generate an APK
4. You can also Build an Run the project using an emulator on Android Studio

## License
[GNUv3](https://github.com/hussu97/MathSmart/blob/master/LICENSE)
