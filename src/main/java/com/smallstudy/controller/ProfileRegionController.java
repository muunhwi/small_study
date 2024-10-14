package com.smallstudy.controller;

import com.smallstudy.dto.RegionDTO;
import com.smallstudy.event.DataInitializedEvent;
import com.smallstudy.security.CustomUser;
import com.smallstudy.service.InterestRegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProfileRegionController {

    private List<RegionDTO> regions;
    private final InterestRegionService interestRegionService;

    @EventListener(DataInitializedEvent.class)
    public void onDataInitialized() {
        this.regions= interestRegionService.getRegionDTOs();
    }

    @GetMapping("/profile/region")
    String interestRegionGet(@AuthenticationPrincipal CustomUser member, Model model) {
        List<RegionDTO> result = interestRegionService.getMemberSelectedRegions(member.getId());
        bindDataToModel(model, result, new RegionDTO());
        return "smallstudy/profile_region";
    }

    @PostMapping("/profile/region")
    String interestRegionPost(@ModelAttribute("region_form") RegionDTO dto,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal CustomUser member,
                              Model model) {

        List<RegionDTO> result = interestRegionService.saveInterestRegion(member.getId(), dto.getCode());
        if(!result.isEmpty()) {
            bindDataToModel(model, result, dto);
            bindingResult.rejectValue("code", "duplicated");
            return "smallstudy/profile_region";
        }

        return "redirect:/profile/region";
    }

    @GetMapping("/profile/region-delete")
    String interestRegionDelete(@RequestParam("code") Long regionCode,
                                @AuthenticationPrincipal CustomUser member) {
        interestRegionService.deleteInterestRegion(member.getId(), regionCode);
        return "redirect:/profile/region";
    }

    private void bindDataToModel(Model model,
                                 List<RegionDTO> selectedRegions,
                                 RegionDTO dto) {
        model.addAttribute("selectedList", selectedRegions);
        model.addAttribute("list", regions);
        model.addAttribute("type", "region");
        model.addAttribute("region_form",dto);
    }

}
