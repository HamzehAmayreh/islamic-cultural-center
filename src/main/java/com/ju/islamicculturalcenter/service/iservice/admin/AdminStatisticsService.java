package com.ju.islamicculturalcenter.service.iservice.admin;

import com.ju.islamicculturalcenter.dto.response.admin.StatisticsResponse;

import java.time.LocalDate;

public interface AdminStatisticsService {

    StatisticsResponse findAllAdminStatistics(LocalDate date);
}
