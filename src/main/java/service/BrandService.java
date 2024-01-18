package service;

import repository.BrandRepository;
import utility.Validation;

import java.util.Scanner;

public class BrandService {
    private final Scanner sc = new Scanner(System.in);
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    private String getWebsiteFromUser(){
    String website;
        while (true) {
        System.out.println("Please enter your website:");
        website = sc.nextLine();
        if(Validation.isValidWebsite(website))
            break;
        else
            System.out.println("plz enter a valid website");
    }
        return website;
}

}
