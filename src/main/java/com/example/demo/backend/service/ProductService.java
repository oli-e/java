package com.example.demo.backend.service;

import com.example.demo.backend.entity.Product;
import com.example.demo.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.search(stringFilter);
        }
    }


    public List<String> showNames() {
        List<String> list = new ArrayList<>();
        for (Product p: productRepository.findAll()){
            String productString = p.getProductName() + "," + p.getProductDescription() + "," + p.getProductPrice() ;
            list.add(productString);
        }
        return list;
    }

    public long count() {return productRepository.count();}

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void save(Product product){
        if (product == null) {
            LOGGER.log(Level.SEVERE, "Something's wrong");
            return;
        }
        productRepository.save(product);
    }


    @PostConstruct
    public void populateTestProductData(){
        if (productRepository.count() == 0){
            productRepository.saveAll(
                    Stream.of(
                            "Jazda samochodem Tesla Model 3, 130 zł, Jazda samochodem Tesla Model 3. Cudowne 14 godzin jazdy samochodem przyszłości. Możliwość wypróbowania nieporównywalnego przyspieszenia najnowszej technologii. Auto jedzie samo!, zdjecie1",
                            "Spa , 230 zł,Cudowne 14 godzin spa. Masaż tylko dla Ciebie. Wszystko czego możesz chcieć aby twoje ciało osiągnęło stan świętego spokoju. Dla panów w czasie oczekiwania na drugie połówki piwo w cenie., zdjecie2",
                            "Obiad w Karczmie, 170 zł, Obiad w Karczmie. Za jedyne 170 złotych oferujemy obiad dla 6 osób. Od kawałków kurczaka aż po całe ziemniaki. W cenie również sałatki oraz kapusta zasmażana. Trunki za dodatkową opłatą, zdjecie3",
                            "Lot Smiglowcem, 330 zł, Lot Śmigłowcem. Za jedyne 330 złotych masz niepowtarzalną okazję wyrwać się z miejskiej dżungli. Startując z Wadowic masz okazję zwiedzić malownicze jeziora Międzybrodzia Bialskiego, zdjecie4")
                            .map(name -> {
                                String[] split = name.split(",");
                                Product product = new Product();
//                                split.toString();
//                                String[] record = name.split(",");
                                product.setProductName(split[0]);
                                product.setProductPrice(split[1]);
                                product.setProductDescription(split[2]);
                                product.setProductImage(split[3]);
                                return product;
                            }).collect(Collectors.toList()));

        }
    }

}
