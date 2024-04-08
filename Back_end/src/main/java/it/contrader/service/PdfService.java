package it.contrader.service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.element.Table;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.TestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.model.Test;
import it.contrader.model.User;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PdfService {
    public static final String filePath = System.getProperty("user.dir") + "/src/main/java/pdf/";

    public void createTest(BloodTestDTO bloodTestDTO, UrineTestDTO urineTestDTO, RegistryDTO registryDTO, TestDTO testDTO) {
        try {
            String fileName = registryDTO.getCf() + "-" + testDTO.getId() + ".pdf";
            PdfDocument pdf = new PdfDocument(new PdfWriter(filePath + fileName));
            Document document = new Document((pdf));
            document.setFontColor(Color.BLACK);

            document.add(new Paragraph("Healthcare - Laboratorio analisi mediche")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));


            Image img = new Image(ImageDataFactory.create("src/main/java/immagini/logo.png"));
            img.setWidth(140);
            img.setHeight(140);
            img.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(img);



            document.add(new Paragraph("Healthcare - Laboratorio analisi mediche")
                    .setFontSize(10)
                    .setFontColor(new DeviceRgb(0, 191, 255))
                    .setMarginBottom(0)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("Sede: Andria")
                    .setFontSize(10)
                    .setMarginTop(0)
                    .setFontColor(new DeviceRgb(0, 191, 255))
                    .setMarginBottom(0)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("Indirizzo: Via Enrico Dandolo 51 - 76123 Andria (BT)")
                    .setFontSize(10)
                    .setMarginTop(0)
                    .setFontColor(new DeviceRgb(0, 191, 255))
                    .setMarginBottom(0)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("Telefono: 0883555602")
                    .setFontSize(10)
                    .setMarginTop(0)
                    .setFontColor(new DeviceRgb(0, 191, 255))
                    .setMarginBottom(0)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("Email: healthcheckcontrader@gmail.com")
                    .setFontSize(10)
                    .setMarginTop(0)
                    .setFontColor(new DeviceRgb(0, 191, 255))
                    .setMarginBottom(0)
                    .setTextAlignment(TextAlignment.RIGHT));


            Map<String, String> patientData = new LinkedHashMap<>();
            patientData.put("Nome", registryDTO.getName());
            patientData.put("Cognome", registryDTO.getSurname());
            patientData.put("Codice Fiscale", registryDTO.getCf());
            patientData.put("Data e luogo di nascita", registryDTO.getBirthDate() + " " + registryDTO.getBirthPlace());
            patientData.put("Nazionalità", registryDTO.getNationality());
            patientData.put("Residenza", registryDTO.getCity());
            patientData.put("Indirizzo", registryDTO.getAddress());
            patientData.put("Gruppo Sanguigno", registryDTO.getBg());
            patientData.put("Genere", registryDTO.getGender().toString());

            document.add(new Paragraph("Anagrafica del paziente").setFontSize(20).setBold().setTextAlignment(TextAlignment.CENTER));

            Table patientTable = new Table(2);
            patientTable.setWidthPercent(100);

            for (Map.Entry<String, String> entry : patientData.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                patientTable.addCell(new Paragraph(key).setBold());
                patientTable.addCell(new Paragraph(value)).setFontSize(13);
            }

            document.add(patientTable);


            document.add(new Paragraph("\n"));


            Map<String, String> testData = new LinkedHashMap<>();

            if (testDTO.getType().equals(Test.TestType.BLOODTEST)) {
                testData.put("ANALISI", "SANGUE");
                testData.put("Globuli Rossi", bloodTestDTO.getRedBloodCell().toString());
                testData.put("Globuli Bianchi", bloodTestDTO.getWhiteBloodCell().toString());
                testData.put("Piastrine", bloodTestDTO.getPlatelets().toString());
                testData.put("Emoglobina", bloodTestDTO.getHemoglobin().toString());
            } else {
                testData.put("ANALISI", "URINE");
                testData.put("Colore", urineTestDTO.getColor().toString());
                testData.put("Ph", urineTestDTO.getPh().toString());
                testData.put("Proteine", urineTestDTO.getProtein().toString());
                testData.put("Emoglobina", urineTestDTO.getHemoglobine().toString());
            }

            document.add(new Paragraph("Referto").setFontSize(20).setBold().setTextAlignment(TextAlignment.CENTER));

            Table testTable = new Table(2);
            testTable.setWidthPercent(100);

            for (Map.Entry<String, String> entry : testData.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                testTable.addCell(new Paragraph(key).setBold());
                testTable.addCell(new Paragraph(value).setFontSize(13));
            }

            document.add(testTable);
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Data Referto: " + testDTO.getDate()).setBold().setFontSize(14).setMarginTop(0));;

            document.close();
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la creazione del PDF", e);
        }
    }


    public byte[] viewPdf(String string) throws IOException {
        String pdfPath = filePath + string + ".pdf";
        byte[] images = Files.readAllBytes(new File(pdfPath).toPath());
        return images;
    }
}
