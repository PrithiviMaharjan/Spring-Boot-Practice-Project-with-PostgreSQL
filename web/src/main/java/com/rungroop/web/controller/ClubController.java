package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.security.SecurityUtil;
import com.rungroop.web.service.ClubService;
import com.rungroop.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            UserEntity user = userService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result) {
        if (result.hasErrors()) return "clubs-create";
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId,
                               Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-edit";
    }

    @PostMapping("clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}")
    public String clubDetail(
            @PathVariable("clubId") long clubId,
            Model model) {

        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            UserEntity user = userService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }

        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }

    @GetMapping("clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/search")
    public String searchClub(
            @RequestParam(value = "query") String query,
            Model model) {
        List<ClubDto> clubs = clubService.searchClub(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}