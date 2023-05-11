package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Boolean existsCampaignByTitleAndAuthor(String title, String author);
    @Query("FROM Campaign c WHERE c.title LIKE %:searchWord% OR c.author LIKE %:searchWord% ")
    List<Campaign> getCampaignsSearch(String searchWord);
}
