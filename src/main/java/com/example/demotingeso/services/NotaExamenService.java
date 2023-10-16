package com.example.demotingeso.services;

import com.example.demotingeso.entities.Estudiante;
import com.example.demotingeso.entities.NotaExamen;
import com.example.demotingeso.repositories.EstudianteRepository;
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
import java.util.List;


@Service
public class NotaExamenService {
    private final NotaExamenRepository notaExamenRepository;
    private final EstudianteService estudianteService;
    private EstudianteRepository estudianteRepository;

    @Autowired
    public NotaExamenService(NotaExamenRepository notaExamenRepository, EstudianteService estudianteService) {
        this.notaExamenRepository = notaExamenRepository;
        this.estudianteService = estudianteService;
        this.estudianteRepository = estudianteRepository;
    }

    public void importarNotasDesdeExcel(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                String rutEstudiante = row.getCell(0).getStringCellValue();
                double puntajeObtenido = row.getCell(1).getNumericCellValue();

                Estudiante estudiante = estudianteRepository.findByRut(rutEstudiante);

                if (estudiante != null) {
                    NotaExamen notaExamen = new NotaExamen();
                    notaExamen.setEstudiante(estudiante);
                    notaExamen.setPuntajeObtenido(puntajeObtenido);
                    notaExamen.setFechaExamen(LocalDate.now());

                    notaExamenRepository.save(notaExamen);
                }
            }
        }
        calcularPromediosNotasEstudiantes();
    }
    private void calcularPromediosNotasEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        for (Estudiante estudiante : estudiantes) {
            List<NotaExamen> notasExamen = notaExamenRepository.findByEstudiante(estudiante);

            double sumaPuntajes = notasExamen.stream().mapToDouble(NotaExamen::getPuntajeObtenido).sum();
            double promedio = notasExamen.isEmpty() ? 0.0 : sumaPuntajes / notasExamen.size();

            estudiante.setPromedioNotas(promedio);
            estudianteRepository.save(estudiante);
        }
    }
    public double calcularPromedioNotasPorMes(List<NotaExamen> notas) {
        double promedio = 0.0;
        int totalNotas = 0;
        LocalDate fechaActual = LocalDate.now();

        for (NotaExamen nota : notas) {
            if (nota.getFechaExamen().getMonth() == fechaActual.getMonth()) {
                promedio += nota.getPuntajeObtenido();
                totalNotas++;
            }
        }

        if (totalNotas > 0) {
            promedio /= totalNotas;
        }

        return promedio;
    }
}



