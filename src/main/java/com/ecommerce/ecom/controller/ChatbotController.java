package com.ecommerce.ecom.controller;

import com.ecommerce.ecom.service.ChatbotService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/stream")
    public ResponseBodyEmitter streamChat(@RequestBody String query) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(0L); // No timeout

        executor.submit(() -> {
            try {
                chatbotService.streamOllamaResponse(query, new OutputStream() {
                    @Override
                    public void write(byte[] b, int off, int len) throws IOException {
                        try {
                            emitter.send(new String(b, off, len));
                        } catch (IllegalStateException e) {
                            System.out.println("❌ Client disconnected, stopping stream");
                            throw e;
                        }
                    }

                    @Override
                    public void write(int b) throws IOException {
                        try {
                            emitter.send(String.valueOf((char) b));
                        } catch (IllegalStateException e) {
                            System.out.println("❌ Client disconnected, stopping stream");
                            throw e;
                        }
                    }
                });
            } catch (Exception e) {
                try {
                    emitter.send("⚠️ Error: " + e.getMessage());
                } catch (IOException ignored) {}
            } finally {
                try {
                    emitter.complete();
                } catch (IllegalStateException ignored) {
                    System.out.println("✅ Stream already closed, safe to ignore");
                }
            }
        });

        emitter.onTimeout(() -> {
            System.out.println("⚠️ Stream timeout");
            emitter.complete();
        });

        emitter.onCompletion(() -> {
            System.out.println("✅ Stream completed normally");
        });

        return emitter;
    }
}
