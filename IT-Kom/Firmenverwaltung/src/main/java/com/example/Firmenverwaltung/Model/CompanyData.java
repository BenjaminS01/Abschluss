package com.example.Firmenverwaltung.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String commpanyName;
    private boolean takesPart;
    private String logoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommpanyName() {
        return commpanyName;
    }

    public void setCommpanyName(String commpanyName) {
        this.commpanyName = commpanyName;
    }

    public boolean isTakesPart() {
        return takesPart;
    }

    public void setTakesPart(boolean takesPart) {
        this.takesPart = takesPart;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
