package service;

import repository.Shareholder_brandsRepository;

import java.util.Scanner;

public class Shareholder_brandsService {
    private final Scanner sc = new Scanner(System.in);
     private final Shareholder_brandsRepository shareholderBrandsRepository;

    public Shareholder_brandsService(Shareholder_brandsRepository shareholderBrandsRepository) {
        this.shareholderBrandsRepository = shareholderBrandsRepository;
    }

}
