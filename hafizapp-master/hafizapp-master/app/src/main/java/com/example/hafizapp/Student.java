package com.example.hafizapp;




public class Student {
    private int id;
    private String name;
    private int age;
    private String className;
    private String sabaq;
    private String sabaqi;
    private String manzil;

    public Student(int id, String name, int age, String className, String sabaq,String sabaqi, String manzil) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.className = className;
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
    }
    public Student(String name, int age, String className, String sabaq,String sabaqi, String manzil) {
        this.name = name;
        this.age = age;
        this.className = className;
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSabaq() {
        return sabaq;
    }

    public void setSabaq(String sabaq) {
        this.sabaq = sabaq;
    }
    public String getSabaqi() {
        return sabaqi;
    }

    public void setSabaqi(String sabaqi) {
        this.sabaqi = sabaqi;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }
}