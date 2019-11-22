package com.myresume.service.impl;

import com.myresume.entity.Profile;
import com.myresume.repository.search.ProfileSearchRepository;
import com.myresume.service.FindProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ElasticSearchIndexingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchIndexingService.class);

    @Value("${index.all.during.startup}")
    private boolean indexingAllDuringStartup;

    @Autowired
    private ProfileSearchRepository profileSearchRepo;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private FindProfileService findProfileService;

    @PostConstruct
    public void postConstruct() {
        if (false) {
            LOGGER.info("Indexing all during startup is ENABLED");
            LOGGER.info("Clear old index");
            if (elasticsearchOperations.indexExists(Profile.class)) {
                elasticsearchOperations.deleteIndex(Profile.class);
            }
            LOGGER.info("Start indexing profile");
            for (Profile p : findProfileService.findAllForIndexing()) {
                p.getHobbies().size();
                profileSearchRepo.save(p);
                LOGGER.info("Successful indexing of profile: " + p.getUid());
            }
            LOGGER.info("Finish index of profiles");
        } else {
            LOGGER.info("Indexing all during startup is DISABLED");
        }
    }
}
