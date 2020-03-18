package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Language;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class LanguageServiceTest {

    @Autowired
    private LanguageService languageService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createLanguage() {

//        Language language = new Language(1, "G++");
//        languageService.createLanguage(language);
//
//        language = new Language(1, "GCC");
//        languageService.createLanguage(language);
//
//        language = new Language(1, "Pascal");
//        languageService.createLanguage(language);
//
//        language = new Language(1, "Java");
//        languageService.createLanguage(language);
//
//        language = new Language(1, "Python3");
//        languageService.createLanguage(language);
    }

    @Test
    public void getLanguageById() {
//        System.out.println(languageService.getLanguageById(1));
//        System.out.println(languageService.getLanguageById(2));
//        System.out.println(languageService.getLanguageById(3));
    }
}