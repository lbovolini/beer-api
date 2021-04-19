package com.github.lbovolini.app.stock.beer.repository;

import com.github.lbovolini.app.stock.beer.entity.Beer;
import com.github.lbovolini.app.stock.beer.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean deleteById(UUID id) {
        Query query = entityManager.createQuery("DELETE FROM Beer WHERE id = :id");
        query.setParameter("id", id);

        return query.executeUpdate() == 1;
    }

    @Override
    public Collection<Beer> findAll() {
        TypedQuery<Beer> query = entityManager.createQuery("SELECT b FROM Beer b", Beer.class);

        return query.getResultList();
    }

    @Override
    public Optional<Beer> findById(UUID id) {
        TypedQuery<Beer> query = entityManager.createQuery("SELECT b FROM Beer b WHERE b.id = :id", Beer.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Beer> findByName(String name) {
        TypedQuery<Beer> query = entityManager.createQuery("SELECT b FROM Beer b WHERE b.name = :name", Beer.class);
        query.setParameter("name", name);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Beer save(Beer beer) {
        return entityManager.merge(beer);
    }

    @Override
    public Beer update(Beer beer) {
        return entityManager.merge(beer);
    }
}
