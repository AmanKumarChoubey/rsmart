package com.ecommerce.ecom.service;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class ChatbotService {

    public void streamOllamaResponse(String prompt, OutputStream output) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("ollama", "run", "mistral", "--stream");
        Process process = pb.start();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            writer.write(prompt);
            writer.newLine();
            writer.flush();
            writer.close();

            String line;
            while ((line = reader.readLine()) != null) {
                output.write((line + "\n").getBytes());
                output.flush();
            }

        } finally {
            process.destroy();
        }
    }
}
