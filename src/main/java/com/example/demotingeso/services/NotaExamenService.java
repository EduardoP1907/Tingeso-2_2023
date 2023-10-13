package com.example.demotingeso.services;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import com.example.demotingeso.repositories.NotaExamenRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Iterator;



@Service
public class NotaExamenService {
    private final NotaExamenRepository notaExamenRepository;
    private final EstudianteService estudianteService;

    @Autowired
    public NotaExamenService(NotaExamenRepository notaExamenRepository, EstudianteService estudianteService) {
        this.notaExamenRepository = notaExamenRepository;
        this.estudianteService = estudianteService;
    }

    public void importarNotasDesdeExcel(MultipartFile archivo) throws IOException {
        try (InputStream inputStream = archivo.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();


                String rut = row.getCell(0).getStringCellValue();
                LocalDate fechaExamen = LocalDate.ofInstant(row.getCell(1).getDateCellValue().toInstant(), ZoneId.systemDefault());
                double puntajeObtenido = row.getCell(2).getNumericCellValue();


                Estudiante estudiante = estudianteService.obtenerestudianteporrut(rut);

                if (estudiante != null) {

                    NotaExamen notaExamen = new NotaExamen();
                    notaExamen.setEstudiante(estudiante);
                    notaExamen.setFechaExamen(fechaExamen);
                    notaExamen.setPuntajeObtenido(puntajeObtenido);
                    notaExamenRepository.save(notaExamen);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


