package com.iticbcn.mywebapp.llibresapp.Controladors;

import com.iticbcn.mywebapp.llibresapp.Serveis.LlibreService;

import jakarta.servlet.http.HttpSession;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/llibres")
public class LlibreController {

  @Autowired
  private LlibreService service;

  @PostMapping("/inserir")
  public String inserirLlibre(
      @RequestParam(name = "titol") String titol,
      @RequestParam(name = "autor") String autor,
      @RequestParam(name = "editorial") String editorial,
      @RequestParam(name = "datapublicacio") String datapublicacio,
      @RequestParam(name = "tematica") String tematica,
      @RequestParam(name = "isbn") String isbn,
      Model model) {
    LocalDate data;

    if (service.validarDataPublicacio(datapublicacio)) {
      data = LocalDate.parse(datapublicacio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } else {
      model.addAttribute("error", "Data de publicació no vàlida");
      return "inserir";
    }
    if (!service.validarISBN(isbn)) {
      model.addAttribute("error", "ISBN no vàlid");
      return "inserir";
    }
    Llibre llibre = new Llibre();
    llibre.setTitol(titol);
    llibre.setAutor(autor);
    llibre.setEditorial(editorial);
    llibre.setDatapublicacio(data);
    llibre.setTematica(tematica);
    llibre.setIsbn(isbn);
    service.save(llibre);
    return "consulta";

  }

  @PostMapping("/cercaid")
  public String cercaId(@RequestParam(name = "idLlibre", required = false) String idLlibre, Model model) {

    int idLlib = 0;
    String message = "";
    boolean llibreErr = false;

    try {
      idLlib = Integer.parseInt(idLlibre);
      Optional<Llibre> llibre = service.findByIdLlibre(idLlib);
      if (llibre.isPresent()) {
        model.addAttribute("llibre", llibre);
      } else {
        message = "No hi ha cap llibre amb aquesta id";
        llibreErr = true;
      }

    } catch (Exception e) {
      message = "La id de llibre ha de ser un nombre enter";
      llibreErr = true;
    }

    model.addAttribute("message", message);
    model.addAttribute("llibreErr", llibreErr);

    return "cercaid";

  }

@PostMapping("/index")
public String login(
    @RequestParam(name = "usuari") String usuari,
    @RequestParam(name = "password") String password,
    HttpSession session,
    Model model) {

    if (usuari.equals("toni") && password.equals("h3ll0!!")) {
        session.setAttribute("usuari", usuari); // Guardar en la sesión
        return "index";
    } else {
        model.addAttribute("error", "Credencials incorrectes");
        return "login";
    }
}

@PostMapping("/logout")
public String logout(HttpSession session, SessionStatus status) {
    session.invalidate(); // Limpiar la sesión
    status.setComplete();
    return "redirect:/";
}

  @GetMapping("/index")
  public String index(HttpSession session, Model model) {
      String usuari = (String) session.getAttribute("usuari");
      if (usuari == null) {
          return "redirect:/"; // Redirigir al inicio de sesión si no hay usuario
      }
      model.addAttribute("usuari", usuari); // Agregar al modelo
      return "index";
  }

  @GetMapping("/")
  public String iniciar(HttpSession session, Model model) {
      System.out.println("Accediendo al root (/)");
      if (session.getAttribute("usuari") != null) {
          System.out.println("Usuario ya autenticado, redirigiendo al index");
          return "redirect:/index";
      }
      System.out.println("Mostrando página de login");
      return "login";
  }

  @GetMapping("/consulta")
  public String consulta(Model model) {

    Set<Llibre> llibres = service.findAll();

    model.addAttribute("llibres", llibres);

    return "consulta";
  }

  @GetMapping("/inserir")
  public String inputInserir(Model model) {

    return "inserir";
  }

  @GetMapping("/cercaid")
  public String inputCerca(Model model) {

    Llibre llibre = new Llibre();
    llibre.setIdLlibre(0);
    model.addAttribute("llibreErr", true);
    model.addAttribute("message", "");
    model.addAttribute("llibre", llibre);

    return "cercaid";
  }

}
