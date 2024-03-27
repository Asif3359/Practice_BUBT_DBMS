package com.example.bubt.Models;

public class Student {

        private int Student_ID;
        private int userID;
        private String name;
        private String email;
        private String intake;
        private String section;
        private String phone;
        private String address;
        private String subject;
        private String password;

        public Student(int Student_ID ,int userID, String name, String email, String intake, String section, String phone, String address, String subject, String password) {
            this.Student_ID=Student_ID;
            this.userID = userID;
            this.name = name;
            this.email = email;
            this.intake = intake;
            this.section = section;
            this.phone = phone;
            this.address = address;
            this.subject = subject;
            this.password = password;
        }

        // Getters and setters
        public  int getStudent_ID(){
            return Student_ID;
        }
        public void setStudent_ID(int Student_ID){
            this.Student_ID=Student_ID;
        }
        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIntake() {
            return intake;
        }

        public void setIntake(String intake) {
            this.intake = intake;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
