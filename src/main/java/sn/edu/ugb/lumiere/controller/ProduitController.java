package sn.edu.ugb.lumiere.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import sn.edu.ugb.lumiere.entity.Produit;
import sn.edu.ugb.lumiere.service.ProduitService;

@RestController
@RequestMapping("/produits")
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    // récupérer tous les produits
    @GetMapping
    public List<Produit> getAllProduits() {
        return service.getAll();
    }

    // récupérer un produit par id
    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id) {
        return service.getById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    // ajouter un produit
    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return service.save(produit);
    }

    // modifier un produit
    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        produit.setId(id);
        return service.save(produit);
    }

    // supprimer un produit
    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        service.delete(id);
    }
}