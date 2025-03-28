package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Set;

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
  public Set<Llibre> findAll() {
    return repo.findAll();
  }

  @Override
  public Llibre findByTitol(String titol) {
    return repo.findByTitol(titol);
  }

  @Override
  public Optional<Llibre> findByIdLlibre(int id) {
    return findByIdLlibre(id);
  }

  @Override
  public Set<Llibre> findByTitolAndEditorial(String titol, String editorial) {
    return repo.findByTitolAndEditorial(titol, editorial);
  }

  /**
   * Valida si un ISBN proporcionat és vàlid.
   * Un ISBN vàlid ha de tenir 13 dígits i pot acabar en un número o 'X'.
   */
  @Override
  public boolean validarISBN(String isbn) {
    return isbn != null && isbn.matches("\\d{13}") && isbn.matches("^(?:\\d{12}[0-9X])$");
  }

  @Override
  public boolean validarDataPublicacio(String dataPublicacio) {
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
