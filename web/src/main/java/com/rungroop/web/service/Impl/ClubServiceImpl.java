package com.rungroop.web.service.Impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.mapper.ClubMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.service.ClubService;
import com.rungroop.web.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.web.mapper.ClubMapper.*;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> club = clubRepository.findAll();
        return club.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        UserEntity user = userRepository.findByEmail(SecurityUtil.getSessionUser());
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        return mapToClubDto(clubRepository.findById(clubId).get());
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        UserEntity user = userRepository.findByEmail(SecurityUtil.getSessionUser());
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClub(String query) {
        return clubRepository.searchClubs(query).stream()
                .map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }
}