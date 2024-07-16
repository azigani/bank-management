package com.alphonse.bankback.constantes;

public enum Fonctionnalites {

    GESTION_UTILISATEURS("/users" ,  "Gestion des utilisateurs"),
    GESTION_JOURNAUX("/logs","Gestion des journaux"),
    GESTION_ROLES("/roles","Gestion des roles");

    String path;
    String label;

    Fonctionnalites(String path,String label) {
        this.path = path;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getPath() {
        return path;
    }
}
