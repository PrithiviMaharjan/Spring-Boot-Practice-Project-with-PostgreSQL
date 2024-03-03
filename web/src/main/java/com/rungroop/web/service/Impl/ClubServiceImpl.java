package com.rungroop.web.service.Impl;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.mapper.ClubMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.web.mapper.ClubMapper.*;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> club = clubRepository.findAll();
        return club.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public ClubDto findClubById(long clubId) {
        return mapToClubDto(clubRepository.findById(clubId).get());
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        clubRepository.save(mapToClub(clubDto));
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
