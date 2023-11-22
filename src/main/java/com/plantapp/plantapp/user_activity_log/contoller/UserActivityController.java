package com.plantapp.plantapp.user_activity_log.contoller;

import com.plantapp.plantapp.user_activity_log.model.UserActivityLog;
import com.plantapp.plantapp.user_activity_log.service.UserActivityLogService;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user-activity-log")
public class UserActivityController {

    private final UserActivityLogService userActivityLogService;
@Autowired
    public UserActivityController(UserActivityLogService userActivityLogService) {
        this.userActivityLogService = userActivityLogService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserActivityLog>> getAllUsersActivityLog(){
    List<UserActivityLog> userActivityLogs = userActivityLogService.getAllUsersLogActivity();
    return ResponseEntity.ok(userActivityLogs);
    }
}
