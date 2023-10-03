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

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


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

                // Suponemos que las columnas son: RUT, Fecha del examen, Puntaje obtenido
                String rut = row.getCell(0).getStringCellValue();
                LocalDate fechaExamen = LocalDate.ofInstant(row.getCell(1).getDateCellValue().toInstant(), ZoneId.systemDefault());
                double puntajeObtenido = row.getCell(2).getNumericCellValue();

                // Obtenemos al estudiante por su RUT
                Estudiante estudiante = estudianteService.obtenerestudianteporut(rut);

                if (estudiante != null) {
                    // Creamos una nueva NotaExamen y la guardamos en la base de datos
                    NotaExamen notaExamen = new NotaExamen();
                    notaExamen.setEstudiante(estudiante);
                    notaExamen.setFechaExamen(fechaExamen);
                    notaExamen.setPuntajeObtenido(puntajeObtenido);
                    notaExamenRepository.save(notaExamen);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // Lanzar la excepción nuevamente para manejarla en el controlador si es necesario
        }
    }
}


