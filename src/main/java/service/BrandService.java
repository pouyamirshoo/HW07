package service;

import repository.BrandRepository;

import java.util.Scanner;

public class BrandService {
    private final Scanner sc = new Scanner(System.in);
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

}
