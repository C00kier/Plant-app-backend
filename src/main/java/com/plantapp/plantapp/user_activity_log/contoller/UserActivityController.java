package com.plantapp.plantapp.user_activity_log.contoller;

import com.plantapp.plantapp.user_activity_log.model.UserActivity;
import com.plantapp.plantapp.user_activity_log.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user-activity-log")
public class UserActivityController {

    private final UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserActivity>> getAllUsersActivityLog(){
    List<UserActivity> userActivities = userActivityService.getAllUsersLogActivity();
    return ResponseEntity.ok(userActivities);
    }
}
