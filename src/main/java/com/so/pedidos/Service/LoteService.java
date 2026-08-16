package com.so.pedidos.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.so.pedidos.Repository.LoteRepository;
import com.so.pedidos.model.Lote;

@Service
public class LoteService {

    private final LoteRepository loteRepository;

    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<Lote> getAlllotes() {
        return (List<Lote>) loteRepository.findAll();
    }

    public Optional<Lote> findById(int par_id) {
        return loteRepository.findById(par_id);
    }

    public Lote save(Lote par_lote) {
        return loteRepository.save(par_lote);
    }

    public void delete(Lote par_lote) {
        loteRepository.delete(par_lote);
    }

}
