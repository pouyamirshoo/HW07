package models;

public class Shareholder_brands {
    Shareholder shareholder;
    Brands brands;

    public Shareholder_brands() {
    }

    public Shareholder_brands(Brands brands) {
        this.brands = brands;
    }

    public Shareholder_brands(Shareholder shareholder) {
        this.shareholder = shareholder;
    }

    public Shareholder_brands(Shareholder shareholder, Brands brands) {
        this.shareholder = shareholder;
        this.brands = brands;
    }

    public Shareholder getShareholder() {
        return shareholder;
    }

    public void setShareholder(Shareholder shareholder) {
        this.shareholder = shareholder;
    }

    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Shareholder_brands{" +
                "shareholder=" + shareholder +
                ", brands=" + brands +
                '}';
    }
}
