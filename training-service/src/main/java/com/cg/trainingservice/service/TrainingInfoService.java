package com.cg.trainingservice.service;

import com.cg.trainingservice.mapper.TrainingInfoMapper;
import com.cg.trainingservice.model.TrainingInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingInfoService {

    private final TrainingInfoMapper trainingInfoMapper;

    public TrainingInfoService(TrainingInfoMapper trainingInfoMapper) {
        this.trainingInfoMapper = trainingInfoMapper;
    }

    public int addTraining(TrainingInfo trainingInfo) {
        return trainingInfoMapper.insert(trainingInfo);
    }

    public int deleteTraining(Integer id) {
        return trainingInfoMapper.deleteById(id);
    }

    public int updateTraining(TrainingInfo trainingInfo) {
        return trainingInfoMapper.update(trainingInfo);
    }

    public TrainingInfo getTrainingById(Integer id) {
        return trainingInfoMapper.findById(id);
    }

    public List<TrainingInfo> getAllTrainings() {
        return trainingInfoMapper.findAll();
    }
}
