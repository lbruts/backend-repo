package com.cg.trainingservice.mapper;


import com.cg.trainingservice.model.TrainingInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainingInfoMapper {

    @Insert("INSERT INTO training_info (project_code, training_project, plan_category, training_time, department, " +
            "implementation_department, training_target, participants, training_location, training_method, training_category, " +
            "training_hours, training_direction, training_level, instructor_qualification, remarks, travel_expense, " +
            "training_fee, total_budget, training_materials, user_id) VALUES " +
            "(#{projectCode}, #{trainingProject}, #{planCategory}, #{trainingTime}, #{department}, " +
            "#{implementationDepartment}, #{trainingTarget}, #{participants}, #{trainingLocation}, #{trainingMethod}, " +
            "#{trainingCategory}, #{trainingHours}, #{trainingDirection}, #{trainingLevel}, #{instructorQualification}, " +
            "#{remarks}, #{travelExpense}, #{trainingFee}, #{totalBudget}, #{trainingMaterials}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TrainingInfo trainingInfo);

    @Delete("DELETE FROM training_info WHERE id = #{id}")
    int deleteById(Integer id);

    @Update("UPDATE training_info SET project_code = #{projectCode}, training_project = #{trainingProject}, " +
            "plan_category = #{planCategory}, training_time = #{trainingTime}, department = #{department}, " +
            "implementation_department = #{implementationDepartment}, training_target = #{trainingTarget}, " +
            "participants = #{participants}, training_location = #{trainingLocation}, training_method = #{trainingMethod}, " +
            "training_category = #{trainingCategory}, training_hours = #{trainingHours}, training_direction = #{trainingDirection}, " +
            "training_level = #{trainingLevel}, instructor_qualification = #{instructorQualification}, remarks = #{remarks}, " +
            "travel_expense = #{travelExpense}, training_fee = #{trainingFee}, total_budget = #{totalBudget}, " +
            "training_materials = #{trainingMaterials}, user_id = #{userId} WHERE id = #{id}")
    int update(TrainingInfo trainingInfo);

    @Select("SELECT * FROM training_info WHERE id = #{id}")
    TrainingInfo findById(Integer id);

    @Select("SELECT * FROM training_info")
    List<TrainingInfo> findAll();
}
