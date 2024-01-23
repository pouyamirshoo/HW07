package service;

import models.Shareholder;
import repository.Shareholder_brandsRepository;
import utility.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Shareholder_brandsService {
    private final Scanner sc = new Scanner(System.in);
     private final Shareholder_brandsRepository shareholderBrandsRepository;
    private final ShareholderService shareholderService = ApplicationContext.getShareholderService();

    public Shareholder_brandsService(Shareholder_brandsRepository shareholderBrandsRepository) {
        this.shareholderBrandsRepository = shareholderBrandsRepository;
    }
    public Shareholder [] makeShareholdersArray(int id) throws SQLException {
        int [] num = shareholderBrandsRepository.allBrandShareHolders(id);
        Shareholder [] shareholders = new Shareholder[num.length];
        for (int i = 0; i < num.length; i++) {
            shareholders[i] = shareholderService.loudOneShareHolderById1(num[i]);
        }
        return shareholders;
    }
}
