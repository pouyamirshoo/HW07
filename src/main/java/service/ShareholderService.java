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
        do {
            System.out.println("Please enter your PhoneNumber:");
            PhoneNumber = sc.nextLine();
            System.out.println("plz enter a valid PhoneNumber");
        } while (!Validation.isValidPhoneNumber(PhoneNumber));
        return PhoneNumber;
    }
    private String getNationalCode() {
        String nationalCode;
        do {
            System.out.println("Please enter your nation code:");
            nationalCode = sc.nextLine();
            System.out.println("plz enter a valid nation code");
        } while (!Validation.isValidNationalCode(nationalCode));
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
        Brands [] brands = shareholderRepository.shareholderBrands(id);
        System.out.println(Arrays.toString(brands));
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
        shareholderRepository.deleteShareholderFromInnerTable(id);
             shareholderRepository.deleteShareholder(id);
                System.out.println("shareholder deleted");
    }
}
