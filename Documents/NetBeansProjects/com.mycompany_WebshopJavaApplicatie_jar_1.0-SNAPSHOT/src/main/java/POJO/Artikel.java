package POJO;


public class Artikel {
    private int artikelId;
    private String artikelNaam;
    private double artikelPrijs;
    
    public Artikel(){
    }
    
    public Artikel(int artikelId, String artikelNaam, double artikelPrijs) {
        this.artikelId = artikelId;
        this.artikelNaam = artikelNaam;
        this.artikelPrijs = artikelPrijs;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public double getArtikelPrijs() {
        return artikelPrijs;
    }

    public void setArtikelId(int artikelID) {
        this.artikelId = artikelID;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public void setArtikelPrijs(double artikelPrijs) {
        this.artikelPrijs = artikelPrijs;
    }
    
}
