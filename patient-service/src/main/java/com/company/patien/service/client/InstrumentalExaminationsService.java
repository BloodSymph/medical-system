package com.company.patien.service.client;

import com.company.patien.dto.client.InstrumentalExaminationsRequest;
import com.company.patien.dto.client.InstrumentalExaminationsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstrumentalExaminationsService {

    List<InstrumentalExaminationsResponse> getInstrumentalExaminations(String username);

    InstrumentalExaminationsResponse createInstrumentalExaminations(
            InstrumentalExaminationsRequest instrumentalExaminationsRequest, String username
    );

    void deleteInstrumentalExaminations(String username, Long version);

}
