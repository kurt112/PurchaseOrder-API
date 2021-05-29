package com.API.PurchaseOrder.service.serviceImplementation;

import com.API.PurchaseOrder.entity.SubSector;
import com.API.PurchaseOrder.repository.SubSectorRepository;
import com.API.PurchaseOrder.service.PageableParentClass.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubSectorService implements Services<SubSector> {

    private final SubSectorRepository repo;

    @Autowired
    public SubSectorService(SubSectorRepository repo) {
        this.repo = repo;
    }


    public List<SubSector> getAll() {
        return repo.findAll();
    }

    @Override
    public Page<SubSector> data(String search, int page, int size,String orderBy, boolean order) {
        Pageable pageable = PageRequest.of(page,size);
        return repo.data(search.trim().isEmpty()? "": search,pageable);
    }

    @Override
    public SubSector save(SubSector subSector) {
        try {
            repo.save(subSector);
        } catch (Exception e) {

            return null;
        }
        return subSector;
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
    public SubSector findById(int id) {
        Optional<SubSector> sector = repo.findById(id);
        return sector.orElse(null);
    }


}
