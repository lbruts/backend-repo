package com.cg.trainingservice.controller;

import com.cg.trainingservice.model.TrainingInfo;
import com.cg.trainingservice.service.TrainingInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingInfoController {

    private final TrainingInfoService trainingInfoService;

    public TrainingInfoController(TrainingInfoService trainingInfoService) {
        this.trainingInfoService = trainingInfoService;
    }

    @PostMapping("/add")
    public String addTraining(@RequestBody TrainingInfo trainingInfo) {
        int result = trainingInfoService.addTraining(trainingInfo);
        return result > 0 ? "培训信息添加成功" : "培训信息添加失败";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTraining(@PathVariable Integer id) {
        int result = trainingInfoService.deleteTraining(id);
        return result > 0 ? "培训信息删除成功" : "培训信息删除失败";
    }

    @PutMapping("/update")
    public String updateTraining(@RequestBody TrainingInfo trainingInfo) {
        int result = trainingInfoService.updateTraining(trainingInfo);
        return result > 0 ? "培训信息更新成功" : "培训信息更新失败";
    }

    @GetMapping("/{id}")
    public TrainingInfo getTrainingById(@PathVariable Integer id) {
        return trainingInfoService.getTrainingById(id);
    }

    @GetMapping("/all")
    public List<TrainingInfo> getAllTrainings() {
        return trainingInfoService.getAllTrainings();
    }
}
