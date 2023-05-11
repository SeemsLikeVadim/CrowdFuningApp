package com.bezkoder.spring.security.jwt.controllers;


import com.bezkoder.spring.security.jwt.models.Campaign;
import com.bezkoder.spring.security.jwt.payload.request.CampaignRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.CampaignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignRepository campaignRepository;

    private final ModelMapper modelMapper;

    public CampaignController(CampaignRepository campaignRepository, ModelMapper modelMapper) {
        this.campaignRepository = campaignRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/info")
    public String allAccess(){
        return "Клиент-серверное приложение краудфандинговой платформы";
    }

    @GetMapping("/Campaing")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Campaign>> getAllCampaings() {
        return ResponseEntity.ok(campaignRepository.findAll());
    }
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Campaign>> getAllCampaignsBySearchWord(@RequestParam String searchWord) {
        System.out.println(campaignRepository.getCampaignsSearch(searchWord));
        return ResponseEntity.ok(campaignRepository.getCampaignsSearch(searchWord));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveCampaign(@RequestBody @Valid CampaignRequest campaignRequest) {
        if (campaignRepository.existsCampaignByTitleAndAuthor(
                campaignRequest.getTitle(),
                campaignRequest.getAuthor()
        )) return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Сбор средств на эту кампанию уже открыт!"));

        Campaign campaign = convertToCampaignDTO(campaignRequest);
        return ResponseEntity.ok(campaignRepository.save(campaign));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateCampaign(@RequestBody @Valid CampaignRequest campaignRequest,
                                        @PathVariable Long id) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        if(campaignOptional.isEmpty()) return ResponseEntity.badRequest()
                .body(new MessageResponse("Error: Кампания не существует"));

        Campaign campaign = campaignOptional.get();
        campaign.setTitle(campaignRequest.getTitle());
        campaign.setDescription(campaignRequest.getDescription());
        campaign.setAuthor(campaignRequest.getAuthor());
        campaign.setCurrentPrice(campaignRequest.getCurrentPrice());
        campaign.setTotalPrice(campaignRequest.getTotalPrice());
        campaign.setEndDate(campaignRequest.getEndDate());

        return ResponseEntity.ok(campaignRepository.save(campaign));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteCampaign(@PathVariable Long id) {
        campaignRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findCampaignById(@PathVariable Long id) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        return campaignOptional.isPresent() ?
                ResponseEntity.ok(campaignOptional.get()) :
                ResponseEntity.badRequest().body(new MessageResponse("Error: Такой кампании не существует"));
    }

    public Campaign convertToCampaignDTO(CampaignRequest campaignDTO) {
        return this.modelMapper.map(campaignDTO, Campaign.class);
    }
}
