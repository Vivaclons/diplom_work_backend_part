package kz.spring.analysisservice.service;

import kz.spring.analysisservice.model.Analysis;
import kz.spring.analysisservice.repository.AnalysisRepository;
import kz.spring.analysisservice.service.impl.IAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService implements IAnalysisService {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Override
    public Analysis getById(Long id) {
        return analysisRepository.getById(id);
    }

    @Override
    public void update(Analysis analysis) {
        analysisRepository.saveAndFlush(analysis);
    }

    @Override
    public List<Analysis> getAllAnalysis() {
        return this.analysisRepository.findAll();
    }

    @Override
    public void DeleteById(Long id) {
        analysisRepository.deleteById(id);
    }
}
