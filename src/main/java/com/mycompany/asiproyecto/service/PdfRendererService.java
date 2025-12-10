package com.mycompany.asiproyecto.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfRendererService {
    public static List<BufferedImage> renderPdfPages(String fileId) throws Exception {
        List<BufferedImage> pageImages = new ArrayList<>();
        // 1. Download the file content stream using the authenticated service
        // We cast the OutputStream (ByteArrayOutputStream) to get the byte array
        // and wrap it in an InputStream for PDDocument.
        try (ByteArrayOutputStream os = (ByteArrayOutputStream) GoogleDriveService.downloadFile(fileId);
             InputStream pdfInputStream = new ByteArrayInputStream(os.toByteArray());
             PDDocument document = PDDocument.load(pdfInputStream)) {

            // 2. Render pages using PDFBox
            PDFRenderer renderer = new PDFRenderer(document);
            float scale = 1.5f; // Rendering resolution (1.5x standard DPI)

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImage(i, scale);
                pageImages.add(image);
            }
            
        } catch (Exception e) {
            System.err.println("Error rendering PDF from Drive ID: " + fileId);
            throw e;
        }

        return pageImages;
    }
}
