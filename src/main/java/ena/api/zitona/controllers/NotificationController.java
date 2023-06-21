package ena.api.zitona.controllers;

import ena.api.zitona.entitys.NotificationMessage;
import ena.api.zitona.services.PushNotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final PushNotificationService pushNotificationService;

    public NotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody NotificationMessage notificationMessage) {
        return pushNotificationService.sendNotificationByToken(notificationMessage);
    }
}
