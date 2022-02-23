package kz.spring.analysisservice.service.impl;

import kz.spring.analysisservice.model.Analysis;

import java.util.List;

public interface IAnalysisService {
    List<Analysis> searchAnalysisByAnalysisDescription(String analysisDescription);
    Analysis getById(Long id);
    void update(Analysis analysis);
    List<Analysis> getAllAnalysis();
    void DeleteById(Long id);
}
