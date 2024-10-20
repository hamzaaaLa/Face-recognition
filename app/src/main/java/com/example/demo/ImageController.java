package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    private WebSocketHandler webSocketHandler;

    private static final String UPLOAD_TOPIC = "facial_recognition_results"; // Updated Kafka topic name
    private static final String DISPLAY_TOPIC = "facial_recognition_results_display";

    private String matchInfo = "";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("matchInfo", matchInfo);
        return "upload"; // Assuming upload.jsp is in /WEB-INF/views/ directory
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile image, Model model) {
        try {
            kafkaTemplate.send(UPLOAD_TOPIC, image.getBytes());
            model.addAttribute("message", "Image uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to upload image: " + e.getMessage());
        }
        return "upload"; // Return the view name where you want to display the message
    }

    @KafkaListener(topics = DISPLAY_TOPIC, groupId = "image-consumer-group")
    public void listen(String message) {
        this.matchInfo = message;
        System.out.println("Received message: " + message); // Logging for debugging
        try {
            webSocketHandler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
