package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageEntity;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class InitService implements CommandLineRunner {

    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WordRepository wordRepository;

    public InitService(LanguageRepository languageRepository, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, WordRepository wordRepository) {

        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wordRepository = wordRepository;
    }

    @Override
    public void run(String... args) {

        initLanguages();
        initUsers();
        initWords();
    }

    public void initLanguages() {

        if (this.languageRepository.count() == 0) {
            final var languageEntities = Arrays.stream(LanguageEnum.values())
                    .map(InitService::mapToLanguageEntity)
                    .toList();

            languageRepository.saveAll(languageEntities);
        }
    }

    private static LanguageEntity mapToLanguageEntity(LanguageEnum v) {

        return new LanguageEntity()
                .setName(v)
                .setDescription(v.getDescription());
    }

    public void initUsers() {

        final UserEntity user = new UserEntity()
                .setUsername("user")
                .setEmail("user@user.com")
                .setPassword(passwordEncoder.encode("1234"));

        final UserEntity other = new UserEntity()
                .setUsername("other")
                .setEmail("other@user.com")
                .setPassword(passwordEncoder.encode("1234"));

        userRepository.saveAllAndFlush(List.of(user, other));
    }

    public void initWords() {

        final UserEntity user = userRepository.findByUsername("user").orElseThrow();
        final UserEntity other = userRepository.findByUsername("other").orElseThrow();

        final WordEntity laienhafte = new WordEntity()
                .setTerm("laienhafte")
                .setLanguage(languageRepository.findByName(LanguageEnum.GERMAN).orElseThrow())
                .setExample("Ich habe noch nie eine so laienhafte Organisation einer Prüfung gesehen!")
                .setAddedBy(user)
                .setInputDate(LocalDate.now().minusDays(1))
                .setTranslation("amateurish");

        user.addWord(laienhafte);

        final WordEntity amateur = new WordEntity()
                .setTerm("amateur")
                .setLanguage(languageRepository.findByName(LanguageEnum.SPANISH).orElseThrow())
                .setExample("¡Nunca había visto una organización de exámenes tan amateur!")
                .setAddedBy(other)
                .setInputDate(LocalDate.now().minusDays(1))
                .setTranslation("amateurish");

        other.addWord(amateur);

        final WordEntity damateur = new WordEntity()
                .setTerm("d'amateur")
                .setLanguage(languageRepository.findByName(LanguageEnum.FRENCH).orElseThrow())
                .setExample("Je n'ai jamais vu une organisation d'examen aussi amateur !")
                .setAddedBy(user)
                .setInputDate(LocalDate.now().minusDays(1))
                .setTranslation("amateurish");

        user.addWord(damateur);

        final WordEntity dilettantistico = new WordEntity()
                .setTerm("dilettantistico")
                .setLanguage(languageRepository.findByName(LanguageEnum.ITALIAN).orElseThrow())
                .setExample("Non ho mai visto un'organizzazione di un esam così dilettantistica!")
                .setAddedBy(other)
                .setInputDate(LocalDate.now().minusDays(1))
                .setTranslation("amateurish");

        other.addWord(dilettantistico);

        this.wordRepository.saveAll(List.of(laienhafte, amateur, damateur, dilettantistico));
        this.userRepository.saveAll(List.of(user, other));
    }


}
