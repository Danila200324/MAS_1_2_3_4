package com.danven.web_library.domain.user;

import com.danven.web_library.domain.report.Report;

import java.time.LocalDateTime;
import java.util.List;

public interface IAdminAccount {

    List<Report> getAllReports();

    String getUniqueKey();

    LocalDateTime getExpirationTimeOfAccount();


}
