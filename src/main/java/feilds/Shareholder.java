package feilds;

import java.util.Arrays;

public class Shareholder {
    int shareHolderId;
    String shareHolderName;
    String shareHolderPhoneNumber;
    String shareHolderNationalCode;
    Brands[]brands;

    public Shareholder() {
    }

    public Shareholder(String shareHolderName, String shareHolderPhoneNumber, String shareHolderNationalCode, Brands[] brands) {
        this.shareHolderName = shareHolderName;
        this.shareHolderPhoneNumber = shareHolderPhoneNumber;
        this.shareHolderNationalCode = shareHolderNationalCode;
        this.brands = brands;
    }

    public Shareholder(int shareHolderId, String shareHolderName, String shareHolderPhoneNumber, String shareHolderNationalCode, Brands[] brands) {
        this.shareHolderId = shareHolderId;
        this.shareHolderName = shareHolderName;
        this.shareHolderPhoneNumber = shareHolderPhoneNumber;
        this.shareHolderNationalCode = shareHolderNationalCode;
        this.brands = brands;
    }

    public int getShareHolderId() {
        return shareHolderId;
    }

    public void setShareHolderId(int shareHolderId) {
        this.shareHolderId = shareHolderId;
    }

    public String getShareHolderName() {
        return shareHolderName;
    }

    public void setShareHolderName(String shareHolderName) {
        this.shareHolderName = shareHolderName;
    }

    public String getShareHolderPhoneNumber() {
        return shareHolderPhoneNumber;
    }

    public void setShareHolderPhoneNumber(String shareHolderPhoneNumber) {
        this.shareHolderPhoneNumber = shareHolderPhoneNumber;
    }

    public String getShareHolderNationalCode() {
        return shareHolderNationalCode;
    }

    public void setShareHolderNationalCode(String shareHolderNationalCode) {
        this.shareHolderNationalCode = shareHolderNationalCode;
    }

    public Brands[] getBrands() {
        return brands;
    }

    public void setBrands(Brands[] brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return "Shareholder{" +
                "shareHolderId=" + shareHolderId +
                ", shareHolderName='" + shareHolderName + '\'' +
                ", shareHolderPhoneNumber='" + shareHolderPhoneNumber + '\'' +
                ", shareHolderNationalCode='" + shareHolderNationalCode + '\''
                 +
                '}';
    }
}
