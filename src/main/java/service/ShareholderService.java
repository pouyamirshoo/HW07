package service;

import models.Brands;
import models.Shareholder;
import repository.ShareholderRepository;
import utility.ApplicationContext;
import utility.Validation;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ShareholderService {
    private final Scanner sc = new Scanner(System.in);
    private final ShareholderRepository shareholderRepository;
    private final BrandService brandService = ApplicationContext.getBrandService();

    public ShareholderService(ShareholderRepository shareholderRepository) {
        this.shareholderRepository = shareholderRepository;
    }
    private String getPhoneNumber() {
        String PhoneNumber;
        while (true) {
            System.out.println("Please enter your PhoneNumber:");
            PhoneNumber = sc.nextLine();
            if(Validation.isValidPhoneNumber(PhoneNumber))
            break;
            else
            System.out.println("plz enter a valid PhoneNumber");
        }
        return PhoneNumber;
    }
    private String getNationalCode() {
        String nationalCode;
        while (true) {
            System.out.println("Please enter your nation code:");
            nationalCode = sc.nextLine();
            if (Validation.isValidNationalCode(nationalCode))
                break;
            else
            System.out.println("plz enter a valid nation code");
        }
        return nationalCode;
    }
    public void saveShareHolder() throws SQLException {
        System.out.println("plz enter the shareholder name");
        String shareholderName = sc.nextLine();
        String shareholderPhone = getPhoneNumber();
        String shareholderCode = getNationalCode();
        Brands [] brands = brandService.makeAnArrayOfBrands();
        Shareholder shareholder = new Shareholder(shareholderName,shareholderPhone,shareholderCode,brands);
        int res = shareholderRepository.save(shareholder);
        if (res == 1){
           Shareholder shareholder1 = shareholderRepository.takeOBJid(shareholder);
           shareholderRepository.saveInMidTable(shareholder1,shareholder);
        }
    }
    public void loudOneShareHolder() throws SQLException {
        System.out.println("plz enter the shareholder name");
        String shareholderName = sc.nextLine();
       Shareholder shareholder = shareholderRepository.loud(shareholderName);
       if(shareholder == null)
           System.out.println("wrong name or there is no shareholder");
       else
        System.out.println(shareholder.toString());
    }
    public void loudOneShareHolderById() throws SQLException {
        System.out.println("plz enter the shareholder id");
        int id = sc.nextInt();
        sc.nextLine();
       Shareholder shareholder = shareholderRepository.loudById(id);
       if(shareholder == null)
           System.out.println("wrong id or there is no shareholder");
       else
        System.out.println(shareholder.toString());
    }
    public Shareholder loudOneShareHolderById1(int id) throws SQLException {
        return shareholderRepository.loudById(id);
    }
    public void brandsOfOneShareHolder() throws SQLException {
        System.out.println("plz enter the id of shareholder");
        int id = sc.nextInt();
        sc.nextLine();
        Shareholder shareholder = loudOneShareHolderById1(id);
        System.out.println("the shareholder");
        System.out.println(shareholder.toString());
        System.out.println("******+++*****+++******++****");
        Brands [] brands = shareholderRepository.shareholderBrands(id);
        for (int i = 0; i < brands.length; i++) {
            System.out.println(brands[i].toString());
        }
    }
    public void deleteOneShareHolder() throws SQLException {
        System.out.println("all shareholders");
        Shareholder [] shareholders = shareholderRepository.showAllShareHolders();
        for (int i = 0; i < shareholders.length ; i++) {
            System.out.println(shareholders[i].toString());
        }
        System.out.println("*****************");
        System.out.println("plz enter the id of shareholder you want delete");
        int id = sc.nextInt();
        sc.nextLine();
       int a = shareholderRepository.deleteShareholderFromInnerTable(id);
       int b =  shareholderRepository.deleteShareholder(id);
       if(a != 0 && b != 0)
                System.out.println("shareholder deleted");
       else
           System.out.println("wrong id");
    }
    public void editShareHolderName() throws SQLException {
        System.out.println("all shareholders");
        Shareholder [] shareholders = shareholderRepository.showAllShareHolders();
        for (int i = 0; i < shareholders.length ; i++) {
            System.out.println(shareholders[i].toString());
        }
        System.out.println("*****************");
        System.out.println("plz enter the id of shareholder you want edit name");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new name for the shareholder");
        String name = sc.nextLine();
        int edit = shareholderRepository.editShareHolderName(id,name);
        if (edit != 0)
            System.out.println("name changed");
        else
            System.out.println("wrong id");
    }
    public void editShareHolderPhone() throws SQLException {
        System.out.println("all shareholders");
        Shareholder [] shareholders = shareholderRepository.showAllShareHolders();
        for (int i = 0; i < shareholders.length ; i++) {
            System.out.println(shareholders[i].toString());
        }
        System.out.println("*****************");
        System.out.println("plz enter the id of shareholder you want edit phone number");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new phone number for the shareholder");
        String name = getPhoneNumber();
        int edit = shareholderRepository.editShareHolderName(id,name);
        if (edit != 0)
            System.out.println("phone changed");
        else
            System.out.println("wrong id");
    }
    public void editShareHolderCode() throws SQLException {
        System.out.println("all shareholders");
        Shareholder [] shareholders = shareholderRepository.showAllShareHolders();
        for (int i = 0; i < shareholders.length ; i++) {
            System.out.println(shareholders[i].toString());
        }
        System.out.println("*****************");
        System.out.println("plz enter the id of shareholder you want edit national code");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("plz enter the new national code for the shareholder");
        String name = getNationalCode();
        int edit = shareholderRepository.editShareHolderName(id,name);
        if (edit != 0)
            System.out.println("code changed");
        else
            System.out.println("wrong id");
    }
    public void editShareHolderBrand() throws SQLException {
        System.out.println("all shareholders");
        Shareholder [] shareholders = shareholderRepository.showAllShareHolders();
        for (int i = 0; i < shareholders.length ; i++) {
            System.out.println(shareholders[i].toString());
        }
        System.out.println("*****************");
        System.out.println("plz enter the id of shareholder you want edit brand");
        int id = sc.nextInt();
        sc.nextLine();
        Shareholder shareholder = loudOneShareHolderById1(id);
        System.out.println("the shareholder");
        System.out.println(shareholder.toString());
        System.out.println("******+++*****+++******++****");
        Brands [] brands = shareholderRepository.shareholderBrands(id);
        for (int i = 0; i < brands.length; i++) {
            System.out.println(brands[i].toString());
        }
        System.out.println("-------------------------------");
        System.out.println("plz enter the id of wrong brand");
        int del = sc.nextInt();
        sc.nextLine();

        System.out.println("plz enter the new brand id for the shareholder");
        int fk = sc.nextInt();
        sc.nextLine();

        Brands brand = brandService.loadBrandById(fk);
        int edit = shareholderRepository.editShareHolderBrands(id,del,fk);
        if (edit != 0)
            System.out.println("brand changed");
        else
            System.out.println("wrong id");
    }
}
