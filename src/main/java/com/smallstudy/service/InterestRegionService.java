package com.smallstudy.service;

import com.smallstudy.domain.InterestRegion;
import com.smallstudy.domain.Member;
import com.smallstudy.domain.MemberInterestRegion;
import com.smallstudy.dto.RegionDTO;
import com.smallstudy.repo.InterestRegionRepository;
import com.smallstudy.repo.MemberInterestRegionRepository;
import com.smallstudy.repo.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InterestRegionService {

    private final MemberRepository memberRepository;
    private final InterestRegionRepository interestRegionRepository;
    private final MemberInterestRegionRepository memberInterestRegionRepository;

    public List<RegionDTO> getRegionDTOs() {
        return  interestRegionRepository
                .findAll()
                .stream()
                .map(this::toRegionDTO)
                .toList();
    }

    public List<RegionDTO> getMemberSelectedRegions(Long memberId) {
        return memberInterestRegionRepository.findInterestRegionsByMemberId(memberId);
    }

    @Transactional
    public List<RegionDTO> saveInterestRegion(Long memberId, Long regionCode) {
        List<MemberInterestRegion> memberInterestRegions = memberInterestRegionRepository.findMemberInterestRegions(memberId);
        Member member;
        if(!memberInterestRegions.isEmpty()) {
            for (MemberInterestRegion memberInterestRegion : memberInterestRegions) {
                InterestRegion ir = memberInterestRegion.getInterestRegion();
                if(regionCode.equals(ir.getId()))
                {
                    return memberInterestRegions
                            .stream()
                            .map(mir -> toRegionDTO(mir.getInterestRegion()))
                            .sorted(Comparator.comparing(RegionDTO::getCode))
                            .toList();
                }
            }
            member = memberInterestRegions.get(0).getMember();
        }
        else {
            Optional<Member> findMember = memberRepository.findById(memberId);
            member = findMember.orElseThrow(() -> new EntityNotFoundException("EntityNotFoundException"));
        }

        Optional<InterestRegion> findRegion = interestRegionRepository.findById(regionCode);
        InterestRegion region = findRegion.orElseThrow(() -> new EntityNotFoundException("EntityNotFoundException"));

        MemberInterestRegion memberInterestRegion = new MemberInterestRegion(member, region);
        memberInterestRegionRepository.save(memberInterestRegion);
        return List.of();
    }

    @Transactional
    public void deleteInterestRegion(Long memberId, Long regionCode) {
        memberInterestRegionRepository.deleteByMemberIdAndRegionId(memberId, regionCode);
    }

    private RegionDTO toRegionDTO(InterestRegion region) {
        return new RegionDTO(region.getId(), region.getRegion());
    }

}
