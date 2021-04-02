package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.Sector;
import com.API.PurchaseOrder.repository.SectorRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@GraphQLApi
public class SectorService implements Services<Sector> {

    final private SectorRepository repo;

    @Autowired
    public SectorService(SectorRepository repo) {
        this.repo = repo;
    }

    @Override

    public Page<Sector> data(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return repo.sectors(search.trim().isEmpty()?"": search, pageable);
    }

    @Override
    public Sector save(Sector sector) {
        try {
            repo.save(sector);
        } catch (Exception e) {

            return null;
        }
        return sector;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            return  false;
        }
        return false;
    }

    @Override
    public Sector findById(int id) {
        Optional<Sector> sector = repo.findById(id);
        return sector.orElse(null);
    }

}
