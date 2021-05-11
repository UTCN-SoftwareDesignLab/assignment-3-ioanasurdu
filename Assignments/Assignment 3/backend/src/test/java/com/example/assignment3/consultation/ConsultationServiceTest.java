package com.example.assignment3.consultation;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.consultation.dto.ConsultationDTO;
import com.example.assignment3.consultation.mapper.ConsultationMapper;
import com.example.assignment3.consultation.repository.ConsultationRepository;
import com.example.assignment3.consultation.service.ConsultationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ConsultationServiceTest {
    @InjectMocks
    private ConsultationService consultationService;

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private ConsultationMapper consultationMapper;


    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        consultationService = new ConsultationService(consultationRepository, consultationMapper);
    }

    @Test
    void findAll() {
        List<ConsultationDTO> consultationDTOS = TestCreationFactory.listOf(ConsultationDTO.class);
        when(consultationService.findAll()).thenReturn(consultationDTOS);

        List<ConsultationDTO> all = consultationService.findAll();

        Assertions.assertEquals(consultationDTOS.size(), all.size());

    }
}
