package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Language;
import me.xiongxuan.xoj.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XiongXuan
 */
@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public Language getLanguageById(Integer languageId) {
        return languageRepository.getOne(languageId);
    }
}
