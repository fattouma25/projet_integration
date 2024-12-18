package com.example.driver.models;

public class BookingRequest {

    private String nomUtilisateur;
    private String emailUtilisateur;
    private Integer nombreDePassagers;
    private String dateTrajet;
    private String message;

    // Getter and Setter for nomUtilisateur
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    // Getter and Setter for emailUtilisateur
    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    // Getter and Setter for nombreDePassagers
    public Integer getNombreDePassagers() {
        return nombreDePassagers;
    }

    public void setNombreDePassagers(Integer nombreDePassagers) {
        this.nombreDePassagers = nombreDePassagers;
    }

    // Getter and Setter for dateTrajet
    public String getDateTrajet() {
        return dateTrajet;
    }

    public void setDateTrajet(String dateTrajet) {
        this.dateTrajet = dateTrajet;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
