package ena.api.zitona.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import ena.api.zitona.entitys.NotificationMessage;
import ena.api.zitona.entitys.User;
import ena.api.zitona.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PushNotificationService {

    private FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public PushNotificationService(FirebaseMessaging firebaseMessaging, UserRepository userRepository) {
        this.firebaseMessaging = firebaseMessaging;
        this.userRepository = userRepository;
    }



    public String sendNotificationByToken(NotificationMessage notificationMessage) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .build();
            // Create a Message

        try {
            User user = userRepository.findById(notificationMessage.getUserId()).orElse(null);
            if (user != null) {
                String deviceToken = user.getDeviceToken();
                if (deviceToken != null) {
                    Message message = Message.builder()
                            .setToken(deviceToken)
                            .setNotification(notification)
                            .build();
                    // Send the Message
                    firebaseMessaging.send(message);
                    return "Success Sending Notification";
                }
                else return "Error Sending Notification token not found";
            }
                else return "Error Sending Notification user not found";

        } catch (FirebaseMessagingException e) {
            // Handle exception
            e.printStackTrace();
            return "Error Sending Notification";
        }
    }
}
