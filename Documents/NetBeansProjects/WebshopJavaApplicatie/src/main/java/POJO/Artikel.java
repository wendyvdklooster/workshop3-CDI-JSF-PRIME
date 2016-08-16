package POJO;


public class Artikel {
    private long artikelId;
    private String artikelNaam;
    private double artikelPrijs;
    
    public Artikel(){
    }
    
    public Artikel(long artikelId, String artikelNaam, double artikelPrijs) {
        this.artikelId = artikelId;
        this.artikelNaam = artikelNaam;
        this.artikelPrijs = artikelPrijs;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelId(long artikelID) {
        this.artikelId = artikelID;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }
    
}
