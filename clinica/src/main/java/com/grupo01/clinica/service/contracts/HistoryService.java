package com.grupo01.clinica.service.contracts;

import com.grupo01.clinica.domain.dtos.req.RecordDTO;
import com.grupo01.clinica.domain.entities.Historic;
import com.grupo01.clinica.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    Historic createHistory(RecordDTO req, User user);
    List<Historic> allHistory();
}
