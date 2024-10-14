package com.smallstudy.controller;

import com.smallstudy.dto.CategoryItemDTO;
import com.smallstudy.event.DataInitializedEvent;
import com.smallstudy.security.CustomUser;
import com.smallstudy.service.CategoryItemService;
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
public class ProfileCategoryController {

    private final CategoryItemService categoryItemService;
    private List<CategoryItemDTO> categoryItems;

    @EventListener(DataInitializedEvent.class)
    public void onDataInitialized() {
        this.categoryItems = categoryItemService.getCategoryItemDTOs();
    }

    @GetMapping("/profile/category")
    String categoryGet(@AuthenticationPrincipal CustomUser member, Model model) {

        List<CategoryItemDTO> result = categoryItemService.getMemberSelectedCategoryItemDTOs(member.getId());
        bindDataToModel(model, result, new CategoryItemDTO());

        return "smallstudy/profile_category";
    }

    @PostMapping("/profile/category")
    String categoryPost(@ModelAttribute("category_form") CategoryItemDTO dto,
                        BindingResult bindingResult,
                        @AuthenticationPrincipal CustomUser member,
                        Model model) {

        List<CategoryItemDTO> result = categoryItemService.saveMemberCategoryItem(member.getId(), dto.getCode());

        if(!result.isEmpty()) {
            bindDataToModel(model, result, dto);
            bindingResult.rejectValue("code", "duplicated");
            return "smallstudy/profile_category";
        }

        return "redirect:/profile/category";
    }

    @GetMapping("/profile/category-delete")
    String categoryDelete(@RequestParam("code") Long categoryItemCode,
                          @AuthenticationPrincipal CustomUser member) {
        categoryItemService.deleteMemberCategoryItem(member.getId(), categoryItemCode);
        return "redirect:/profile/category";
    }

    private void bindDataToModel(Model model, List<CategoryItemDTO> selectedCategoryItem, CategoryItemDTO dto) {
        model.addAttribute("selectedList", selectedCategoryItem);
        model.addAttribute("list", categoryItems);
        model.addAttribute("type", "category");
        model.addAttribute("category_form", dto);
    }

}
