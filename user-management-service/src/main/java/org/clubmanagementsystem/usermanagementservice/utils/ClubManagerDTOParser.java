package org.clubmanagementsystem.usermanagementservice.utils;

import org.clubmanagementsystem.usermanagementservice.dto.ClubManagerDTO;
import org.clubmanagementsystem.usermanagementservice.model.ClubManager;

public class ClubManagerDTOParser {
    public static ClubManagerDTO toDTO(ClubManager clubManager) {
        ClubManagerDTO dto = new ClubManagerDTO();
        dto.setId(clubManager.getId());
        dto.setName(clubManager.getName());
        dto.setEmail(clubManager.getEmail());
        dto.setClubId(clubManager.getClubIds());
        return dto;
    }
}
