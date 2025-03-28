package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.util.Optional;
import java.util.Set;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

public interface LlibreService {
  Set<Llibre> findAll();
  Llibre findByTitol(String titol);
  Optional<Llibre> findByIdLlibre(int id);
  Set<Llibre> findByTitolAndEditorial(String titol, String editorial);
  void save(Llibre llibre);
  boolean validarISBN(String isbn);
  boolean validarDataPublicacio(String dataPublicacio);
}
