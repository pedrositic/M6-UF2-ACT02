package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Repositoris.Repo;

@Service
public class LlibreServiceImpl implements LlibreService {
  private Repo repo;

  public LlibreServiceImpl(Repo repo) {
    this.repo = repo;
  }

  @Override
  public ArrayList<Llibre> findAll() {
    ArrayList<Llibre> result = repo.findAll();
    if (result == null || result.isEmpty()) {
      System.out.println("El repositorio devolvió una colección vacía o nula");
    }
    return result;
  }

  @Override
  public Llibre findByTitol(String titol) {
    return repo.findAll().stream()
        .filter(llibre -> llibre.getTitol().equalsIgnoreCase(titol))
        .findFirst()
        .orElse(null);
  }

  public Optional<Llibre> findByIdLlibre(int id) {
    return repo.findById(id);
  }

  @Override
  public ArrayList<Llibre> findByTitolAndEditorial(String titol, String editorial) {
    ArrayList<Llibre> result = new ArrayList<>();
    repo.findAll().stream()
        .filter(llibre -> llibre.getTitol().equalsIgnoreCase(titol) &&
            llibre.getEditorial().equalsIgnoreCase(editorial))
        .forEach(result::add);
    return result;
  }

  /**
   * Valida si un ISBN proporcionat és vàlid.
   * Un ISBN vàlid ha de tenir 13 dígits i pot acabar en un número o 'X'.
   */
  @Override
  public boolean validarISBN(String isbn) {
    return isbn != null && isbn.matches("\\d{13}");
  }

  @Override
  public boolean validarDataPublicacio(String dataPublicacio) {
    System.out.println("Validant data de publicació: " + dataPublicacio);
    try {
      LocalDate.parse(dataPublicacio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  @Override
  public void save(Llibre llibre) {
    repo.save(llibre);
  }
}
