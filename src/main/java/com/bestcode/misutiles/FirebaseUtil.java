/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bestcode.misutiles;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jimmy
 */
public class FirebaseUtil {

    public static boolean sendPushViaFirebase(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("llave", "valor");
        Notification notification = Notification.builder()
                .setTitle("Este es el título")
                .setBody("Este es el mensaje")
                .build();
        Message message = Message.builder()
                .putAllData(map)
                .putData("priority", "high")
                .setToken(token) // deviceId
                .setNotification(notification)
                .build();
        try {
            FirebaseApp firebaseApp = FirebaseApp.initializeApp("nombre-app-fcm");
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            System.out.println("Push notification Message sended succesfully. Response {}");
            return true;
        } catch (FirebaseMessagingException ex) {
            ex.printStackTrace();
            System.out.println("Error trying send push notification via firebase to token {}, check details: : "+ex.getMessage());
        }
        return false;
    }
}
