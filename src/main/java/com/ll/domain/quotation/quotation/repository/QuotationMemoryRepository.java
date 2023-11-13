package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuotationMemoryRepository implements QuotationRepository{
    private final List<Quotation> quotations;
    private long lastId;

    public QuotationMemoryRepository() {
        quotations = new ArrayList<>();
        lastId = 0;
    }

    public List<Quotation> findAll() {
        return quotations;
    }

    public void delete(final Quotation quotation) {
        quotations.remove(quotation);
    }

    public Optional<Quotation> findById(final long id) {

        return quotations
                .stream()
                .filter(_quotation -> _quotation.getId() == id)
                .findFirst();
    }

    public void save(final Quotation quotation) {
        if (quotation.getId() == null) {
            quotation.setId(++lastId);
            quotations.add(quotation);
        } else {

        }
    }
}
