package com.company.patien.service.client.implementation;

import com.company.patien.repository.InstrumentalExaminationsRepository;
import com.company.patien.service.client.InstrumentalExaminationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrumentalExaminationsServiceImpl implements InstrumentalExaminationsService {

    private final InstrumentalExaminationsRepository instrumentalExaminationsRepository;

}
