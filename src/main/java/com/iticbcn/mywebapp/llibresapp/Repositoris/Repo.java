package com.iticbcn.mywebapp.llibresapp.Repositoris;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;
import org.springframework.data.repository.CrudRepository;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

@Repository
public interface Repo extends CrudRepository<Llibre, Integer> {

  @Override
  @NonNull
  ArrayList<Llibre> findAll();

  Llibre findByTitol(String titol);

  ArrayList<Llibre> findByTitolAndEditorial(String titol, String editorial);

  Optional<Llibre> findById(int id);
}
