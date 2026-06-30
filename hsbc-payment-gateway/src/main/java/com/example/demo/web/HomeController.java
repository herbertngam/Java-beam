package com.example.demo.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles the simple page routes for this demo.
 *
 * Each method returns a Thymeleaf template name (matching a file under
 * src/main/resources/templates/), and Spring Boot resolves it to the
 * corresponding .html file automatically.
 *
 * Access rules for these routes are defined separately, in SecurityConfig.
 */
@Controller
public class HomeController {

  /**
   * The protected dashboard. SecurityConfig requires the user to be
   * logged in before this method is even called — if they're not,
   * Spring Security redirects them to /login first.
   */
  @GetMapping("/")
  public String landing(Model model, Principal principal) {
    // `principal` is non-null only when a user is logged in.
    model.addAttribute("username", principal != null ? principal.getName() : "Customer");
    return "index"; // renders templates/index.html
  }

  /**
   * A page anyone can visit, logged in or not.
   */
  @GetMapping("/public")
  public String publicPage() {
    return "public"; // renders templates/public.html
  }

  /**
   * An admin-only page. SecurityConfig blocks any user without the
   * ADMIN role from ever reaching this method.
   */
  @GetMapping("/admin")
  public String adminPage(Model model, Principal principal) {
    model.addAttribute("username", principal != null ? principal.getName() : "Admin");
    return "admin"; // renders templates/admin.html
  }
}
