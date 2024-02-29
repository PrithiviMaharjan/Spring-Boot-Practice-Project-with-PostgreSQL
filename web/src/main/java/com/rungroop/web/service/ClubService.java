package com.rungroop.web.service;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    void saveClub(ClubDto club);
    ClubDto findClubById(long clubId);
    void updateClub(ClubDto clubDto);
    void delete(long clubId);

    List<ClubDto> searchClub(String query);
}
