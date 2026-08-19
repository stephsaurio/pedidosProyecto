package com.so.pedidos.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.so.pedidos.Service.LoteService;
import com.so.pedidos.model.Lote;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lote")
public class LoteController {

        private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping("/listlote")
    public List<Lote> getAlllote() {
        return loteService.getAlllotes();
    }

    @GetMapping("/{par_id}")
    public Lote getLoteById(@PathVariable("par_id") int loteId) {
        Optional<Lote> loteOptional = loteService.findById(loteId);
        return loteOptional.orElse(null);
    }

    @PostMapping("/create_lote")
    public Lote createLote(@RequestBody Lote par_lote) {

        if (par_lote != null && loteService.findById(par_lote.getIdlote()).isPresent()) {
            return null;
        } else {
            return loteService.save(par_lote);
        }
    }

    @PutMapping("/update/{par_id}")
    public Lote updateLote(@PathVariable("par_id") int loteId, @RequestBody Lote updatedLote) {

        Optional<Lote> loteOptional = loteService.findById(loteId);
        if (loteOptional.isPresent()) {
            Lote lote = loteOptional.get();
            lote.setEstilo(updatedLote.getEstilo());
            lote.setStock(updatedLote.getStock());
            lote.setTalla(updatedLote.getTalla());
            return loteService.save(lote);
        } else {
            return null;
        }
    }

    @PatchMapping("/update_partial_lote/{par_id}")
    public Lote partialUpdateLote(@PathVariable int par_id, @RequestBody Map<String, Object> updates) {
        Optional<Lote> loteOptional = loteService.findById(par_id);

        if (loteOptional.isPresent()) {
            Lote lote = loteOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "estilo":
                        lote.setEstilo((String) value);                        
                        break;
                    case "stock":
                        lote.setStock(Integer.parseInt((String) value));                                                
                        break;
                    case "talla":
                        lote.setTalla(Double.parseDouble((String) value));
                        break;
                }
            });

            return loteService.save(lote);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete_lote/{id}")
    public void deletelote(@PathVariable("id") int loteId) {
        Optional<Lote> loteOptional = loteService.findById(loteId);
        if(loteOptional.isPresent()){
            loteService.delete(loteOptional.get());
        }
    }

}