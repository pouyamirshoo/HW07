package service;


import models.Brands;
import models.Shareholder;
import repository.BrandRepository;
import utility.ApplicationContext;
import utility.Validation;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class BrandService {
    private final Scanner sc = new Scanner(System.in);
    private final BrandRepository brandRepository;
    private final ShareholderService shareholderService = ApplicationContext.getShareholderService();

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    private String getWebsiteFromUser(){
    String website;
    while (true){
            System.out.println("Please enter your website:");
            website = sc.nextLine();
            if (Validation.isValidWebsite(website))
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
    public Brands loadBrandById(int id) throws SQLException {
        return brandRepository.loadById(id);
    }
    public void loadAllBrands() throws SQLException {
        Brands[]brands = brandRepository.loadAll();
        for (int i = 0; i < brands.length; i++) {
            System.out.println(brands[i].toString());
        }
    }
    public Brands getBrand(int id) throws SQLException {
        return brandRepository.takeBrand(id);
    }
    public void deleteOneBrand() throws SQLException {
        System.out.println("plz enter the brand id you want to delete");
        int id = sc.nextInt();
        sc.nextLine();
        int del = brandRepository.deleteBrandInnerTable(id);
        int del2 = brandRepository.deleteBrandInnerTable2(id);
        int del3 = brandRepository.deleteBrand(id);
        if(del != 0 && del2 != 0 && del3 != 0)
            System.out.println("the brand has been deleted");
        else
            System.out.println("enter the valid id");
    }
    public void editBrandName() throws SQLException {
        System.out.println("plz enter your brand id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter your new brand name");
        String newBrandName = sc.nextLine();
        int edit = brandRepository.editBrandName(id,newBrandName);
        if(edit == 1)
            System.out.println("your brand name has changed");
        else
            System.out.println("try again");
    }
    public void editBrandWebsite() throws SQLException {
        System.out.println("plz enter your brand id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter your new brand website");
        String newBrandWebsite = getWebsiteFromUser();
        int edit = brandRepository.editBrandWebsite(id,newBrandWebsite);
        if(edit == 1)
            System.out.println("your brand website has changed");
        else
            System.out.println("try again");
    }
    public void editBrandDescription() throws SQLException {
        System.out.println("plz enter your brand id");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter your new brand description");
        String newBrandDescription = sc.nextLine();
        int edit = brandRepository.editBrandDescription(id,newBrandDescription);
        if(edit == 1)
            System.out.println("your brand description has changed");
        else
            System.out.println("try again");
    }
    public Brands[] makeAnArrayOfBrands() throws SQLException {
        System.out.println("plz enter the number of brands for shareholder");
        int num = sc.nextInt();
        sc.nextLine();
        Brands [] brands = new Brands[num];
        for (int i = 0; i < num; i++) {
            System.out.println("plz enter the "+ (i+1) +"th brand name");
            String temp = sc.nextLine();
            brands[i] = brandRepository.load(temp);
        }
        return brands;
    }
}
