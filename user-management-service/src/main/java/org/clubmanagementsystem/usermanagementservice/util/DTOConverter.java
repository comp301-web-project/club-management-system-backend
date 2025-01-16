package org.clubmanagementsystem.usermanagementservice.util;

import org.clubmanagementsystem.usermanagementservice.model.ClubManager;
import org.clubmanagementsystem.clubmanagementservice.dto.ClubManagerDTO;

public class DTOConverter {
    public static ClubManagerDTO convertToDTO(ClubManager clubManager) {
        ClubManagerDTO dto = new ClubManagerDTO();
        dto.setId(clubManager.getId());
        dto.setName(clubManager.getName());
        dto.setClubId(clubManager.getClubIds());
        return dto;
    }
}