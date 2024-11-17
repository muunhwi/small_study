package com.smallstudy.utils;

import com.smallstudy.domain.category_entity.Category;
import com.smallstudy.domain.category_entity.CategoryItem;
import com.smallstudy.domain.region_entity.InterestRegion;
import com.smallstudy.domain.region_entity.Region;
import com.smallstudy.repo.category_repo.CategoryRepository;
import com.smallstudy.repo.InterestRegionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParseService {

    @Value("${regions}")
    private String regionFile;
    @Value("${category}")
    private String categoryFile;
    private final InterestRegionRepository interestRegionRepository;
    private final CategoryRepository categoryRepository;

    public void parsingRegion() throws IOException {

        Path path = Paths.get(regionFile);
        List<String> strings = Files.readAllLines(path);

        ArrayList<InterestRegion> regions = new ArrayList<>();
        String city;

        for(int i = 1; i < strings.size() ; ++i) {
            city = strings.get(i - 1);
            if(city.startsWith("@")) {
                city = city.substring(1);
                while(i < strings.size() && !strings.get(i).startsWith("@")) {
                    String s2 = strings.get(i);

                    int pos = s2.indexOf("-");

                    if(pos < 0) {
                        regions.add(new InterestRegion(new Region(city, "", "")));
                        ++i;
                        break;
                    }

                    String gu = s2.substring(0, pos);

                    String s3 = s2.substring(pos + 1);
                    String[] dongs = s3.split("\\s*,\\s*");

                    for (String dong : dongs)
                        regions.add(new InterestRegion(new Region(city, gu, dong.trim())));
                    ++i;
                }
            }
            else
                ++i;
        }
        interestRegionRepository.saveAll(regions);
    }

    public void parsingCategory() throws IOException {

        Path path = Paths.get(categoryFile);
        List<String> strings = Files.readAllLines(path);
        ArrayList<Category> categories = new ArrayList<>();

        for(int i = 1; i < strings.size(); ++i) {

            String s1 = strings.get(i-1);
            String categoryName = s1.substring(s1.indexOf("@") + 1);

            Category category = new Category(categoryName);
            categories.add(category);

            while(i < strings.size() && !strings.get(i).startsWith("@")) {
                String[] categoryItemNames = strings.get(i).split(",");
                for (String categoryItemName : categoryItemNames) {
                    CategoryItem categoryItem = new CategoryItem(category, categoryItemName);
                    category.setCategoryItem(categoryItem);
                }
                ++i;
            }
        }
        categoryRepository.saveAll(categories);
    }


}
