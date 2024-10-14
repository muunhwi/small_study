package com.smallstudy.service;


import com.smallstudy.domain.*;
import com.smallstudy.dto.CategoryItemDTO;
import com.smallstudy.dto.RegionDTO;
import com.smallstudy.repo.CategoryItemRepository;
import com.smallstudy.repo.MemberCategoryItemRepository;
import com.smallstudy.repo.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryItemService {

    private final CategoryItemRepository categoryItemRepository;
    private final MemberCategoryItemRepository memberCategoryItemRepository;
    private final MemberRepository memberRepository;

    public List<CategoryItemDTO> getCategoryItemDTOs() {
        return categoryItemRepository.findAllCategoryItemsWithCategory()
                .stream()
                .map(this::toCategoryItemDTOs)
                .toList();
    }

    public List<CategoryItemDTO> getMemberSelectedCategoryItemDTOs(Long memberId) {
        return memberCategoryItemRepository.findCategoryItemDTOsByMemberId(memberId);
    }


    @Transactional
    public List<CategoryItemDTO> saveMemberCategoryItem(Long memberId, Long categoryCode) {
        List<MemberCategoryItem> memberCategoryItems = memberCategoryItemRepository.findMemberCategoryItems(memberId);
        Member member;

        if(!memberCategoryItems.isEmpty()) {

            for (MemberCategoryItem memberCategoryItem : memberCategoryItems) {
                CategoryItem categoryItem = memberCategoryItem.getCategoryItem();
                if(categoryCode.equals(categoryItem.getId()))
                {
                    return memberCategoryItems
                            .stream()
                            .map(mci -> toCategoryItemDTOs(mci.getCategoryItem()))
                            .sorted(Comparator.comparing(CategoryItemDTO::getCode))
                            .toList();
                }
            }

            member = memberCategoryItems.get(0).getMember();
        } else {
            Optional<Member> findMember = memberRepository.findById(memberId);
            member = findMember.orElseThrow(() -> new EntityNotFoundException(String.format("Member [ID:%d] Not Found", memberId)));
        }

        Optional<CategoryItem> findCategoryItem = categoryItemRepository.findById(categoryCode);
        CategoryItem categoryItem = findCategoryItem.orElseThrow(() -> new EntityNotFoundException(String.format("CategoryItem [ID:%d] Not Found", categoryCode)));

        MemberCategoryItem memberCategoryItem = new MemberCategoryItem(member, categoryItem);
        memberCategoryItemRepository.save(memberCategoryItem);
        return List.of();
    }

    @Transactional
    public void deleteMemberCategoryItem(Long memberId, Long categoryCode) {
        memberCategoryItemRepository.deleteByMemberIdAndCategoryItemId(memberId, categoryCode);
    }

    private CategoryItemDTO toCategoryItemDTOs(CategoryItem item) {
        return new CategoryItemDTO(item.getId(), item.getCategory().getCategoryName(), item.getCategoryItemName());
    }

}
