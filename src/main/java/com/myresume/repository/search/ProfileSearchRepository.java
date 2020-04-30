package com.myresume.repository.search;

import com.myresume.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileSearchRepository extends ElasticsearchRepository<Profile, Long> {

    Page<Profile> findByFirstNameLikeOrLastNameLikeOrObjectiveLikeOrSummaryLikeOrInfoLikeOrCertificatesNameLikeOrLanguagesNameLikeOrPracticesCompanyLikeOrPracticesPositionLikeOrPracticesResponsibilitiesLikeOrSkillsValueLike(
            String firstName, String LastName, String objective, String info, String summary, String certificateName, String languageName, String practicesCompany,
            String practicesPosition, String practicesResponsibility, String skillValue, Pageable pageable);
//
    Page<Profile> findByFirstName(String firstName, Pageable pageable);
}
