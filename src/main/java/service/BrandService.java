package service;


import feilds.Brands;
import feilds.Category;
import feilds.Users;
import repository.BrandRepository;
import utility.Validation;

import java.sql.SQLException;
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
    public void saveBrand() throws SQLException {
        System.out.println("plz enter your brand name");
        String brandName = sc.nextLine();
        String website = getWebsiteFromUser();
        System.out.println("plz enter your description");
        String description = sc.nextLine();

        Brands brand = new Brands(brandName,website,description);
        int signIn =  brandRepository.save(brand);
        if(signIn == 1){
            System.out.println("thank you for make a brand");
        }
        else
            System.out.println("this brand already made");
    }
    public void loadBrand() throws SQLException {
        System.out.println("plz enter your brand name");
        String brandName = sc.nextLine();

        Brands brand = brandRepository.load(brandName);
        if(brand == null)
            System.out.println("wrong brand name or make a brand first");
        else {
            System.out.println("welcome");
            System.out.println(brand.toString());
        }
    }
    public void loadAllBrands() throws SQLException {
        Brands[]brands = brandRepository.loadAll();
        for (int i = 0; i < brands.length; i++) {
            System.out.println(brands[i].toString());
        }
    }
    public Brands getBrand(int id) throws SQLException {
        Brands brand  = brandRepository.takeBrand(id);
        return brand;
    }
}
