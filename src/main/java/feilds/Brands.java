package feilds;

public class Brands {
    int brandId;
    String brandName;
    String website;
    String description;

    public Brands() {
    }

    public Brands(String brandName, String website, String description) {
        this.brandName = brandName;
        this.website = website;
        this.description = description;
    }

    public Brands(int brandId, String brandName, String website, String description) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.website = website;
        this.description = description;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
