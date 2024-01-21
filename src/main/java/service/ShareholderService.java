package service;

import models.Brands;
import models.Shareholder;
import repository.ShareholderRepository;
import utility.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class ShareholderService {
    private final Scanner sc = new Scanner(System.in);
    private final ShareholderRepository shareholderRepository;
    private final BrandService brandService = ApplicationContext.getBrandService();

    public ShareholderService(ShareholderRepository shareholderRepository) {
        this.shareholderRepository = shareholderRepository;
    }
    public void saveShareHolder() throws SQLException {
        System.out.println("plz enter the shareholder name");
        String shareholderName = sc.nextLine();
        System.out.println("plz enter the shareholder phone");
        String shareholderPhone = sc.nextLine();
        System.out.println("plz enter the shareholder national code");
        String shareholderCode = sc.nextLine();
        Brands [] brands = brandService.makeAnArrayOfBrands();
        Shareholder shareholder = new Shareholder(shareholderName,shareholderPhone,shareholderCode,brands);
        int res = shareholderRepository.save(shareholder);
        if (res == 1){
           Shareholder shareholder1 = shareholderRepository.takeOBJid(shareholder);
           shareholderRepository.saveInMidTable(shareholder1,shareholder);
        }
    }
}
