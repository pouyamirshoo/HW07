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

}
