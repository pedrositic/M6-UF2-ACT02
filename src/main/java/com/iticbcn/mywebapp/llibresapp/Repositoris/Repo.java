package com.iticbcn.mywebapp.llibresapp.Repositoris;

import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;
import org.springframework.data.repository.CrudRepository;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

@Repository
public interface Repo extends CrudRepository<Llibre, Integer> {

  @Override
  @NonNull
  Set<Llibre> findAll();

  Llibre findByTitol(String titol);

  Set<Llibre> findByTitolAndEditorial(String titol, String editorial);
}
