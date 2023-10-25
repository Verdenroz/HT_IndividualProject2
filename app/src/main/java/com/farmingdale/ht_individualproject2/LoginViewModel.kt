package com.farmingdale.ht_individualproject2

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//Handles logic behind login and registration
object LoginViewModel : ViewModel() {

    private var firstName by mutableStateOf("")
    private var lastName by mutableStateOf("")
    private var email by mutableStateOf("")
    private var password by mutableStateOf("")
    private var month by mutableStateOf("")
    private var day by mutableStateOf("")
    private var year by mutableStateOf("")

    private var firstNameValid by mutableStateOf(true)
    private var lastNameValid by mutableStateOf(true)
    private var emailValid by mutableStateOf(true)
    private var passwordValid by mutableStateOf(true)
    private var monthValid by mutableStateOf(true)
    private var dayValid by mutableStateOf(true)
    private var yearValid by mutableStateOf(true)

    private var emailInput by mutableStateOf("")
    private var passwordInput by mutableStateOf("")

    fun updateFirstName(name: String?){
        if (name != null) {
            firstName = name
        }
    }
    fun updateLastName(name: String?){
        if (name != null) {
            lastName = name
        }
    }
    fun updateEmail(email: String?){
        if (email != null) {
            this.email = email
        }
    }
    fun updatePassword(pswd: String?){
        if (pswd != null) {
            password = pswd
        }
    }
    fun updateMonth(month: String?){
        if (month != null) {
            this.month = month
        }
    }
    fun updateDay(day: String?){
        if (day != null) {
            this.day = day
        }
    }
    fun updateYear(year: String?){
        if (year != null) {
            this.year = year
        }
    }
    fun updateEmailInput(input: String){
        emailInput = input
    }
    fun updatePswdInput(input: String){
        passwordInput = input
    }

    fun getFN(): String{
        return firstName
    }
    fun getLN(): String{
        return lastName
    }
    fun getMail(): String{
        return email
    }
    fun getPswd(): String{
        return password
    }
    fun getBirthMonth(): String{
        return month
    }
    fun getBirthDay(): String{
        return day
    }
    fun getBirthYear(): String{
        return  year
    }
    fun getMailInput(): String{
        return emailInput
    }
    fun getPswdInput(): String{
        return passwordInput
    }

    fun getValidityFN(): Boolean{
        return firstNameValid
    }
    fun getValidityLN(): Boolean{
        return lastNameValid
    }
    fun getValidityEmail(): Boolean{
        return emailValid
    }
    fun getValidityPswd(): Boolean{
        return passwordValid
    }
    fun getValidityMonth(): Boolean{
        return monthValid
    }
    fun getValidityDay(): Boolean{
        return dayValid
    }
    fun getValidityYear(): Boolean{
        return yearValid
    }

    fun validateLogin(): Boolean{
        if(Patterns.EMAIL_ADDRESS.matcher(getMailInput()).matches()){
            if(getMailInput() == getMail() && getPswdInput() == getPswd()){
                return true
            }
        }
        return false
    }

    fun clearData(){
        updateFirstName("")
        updateLastName("")
        updateEmail("")
        updatePassword("")
        updateMonth("")
        updateDay("")
        updateYear("")
    }

    fun validateData(): Boolean{
        firstNameValid = firstName.length in 3..30
        lastNameValid = lastName.length in 3..30
        passwordValid = password.isNotBlank()
        emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        //validate month
        try{
            monthValid = month.toInt() in 1..12
        }
        catch (e: NumberFormatException){
            monthValid = false
        }
        //validate day
        try{
            dayValid = when(month.toInt()){
                1, 3, 5, 7, 8, 10, 12 -> day.toInt() in 1..31
                4, 6, 9, 11 -> day.toInt() in 1..30
                2 -> day.toInt() in 1..29
                else -> false
            }
        }
        catch (e: NumberFormatException){
            dayValid = false
        }
        //validate year
        try{
            yearValid = year.toInt() in 1900..2100
        }
        catch (e: NumberFormatException){
            yearValid = false
        }
        Log.d("Register", "First Name: $firstName Last Name: $lastName Password: $password Email: $email Month: $month Day: $day Year: $year")
        //returns true only if all the data is valid
        return firstNameValid && lastNameValid && passwordValid && emailValid && monthValid && dayValid && yearValid
    }



}