package com.apifinanceapp.financeapp.model.common;

public enum Languaje {
    EN, ES; // Definición de los valores del enum

    // Método opcional para obtener el valor en minúsculas
    public String getLowerCase() {
        return this.name().toLowerCase();
    }
}