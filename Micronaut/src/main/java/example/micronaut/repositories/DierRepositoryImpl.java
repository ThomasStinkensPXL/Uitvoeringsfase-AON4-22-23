package example.micronaut.repositories;

import example.micronaut.ApplicationConfiguration;
import example.micronaut.SortingAndOrderArguments;
import example.micronaut.domain.Dier;
import example.micronaut.domain.Schuur;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class DierRepositoryImpl implements DierRepository {
    private static final List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");

    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public DierRepositoryImpl(EntityManager entityManager,
                              ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
//        this.schuurRepository = schuurRepository;
    }

    @Override
    @ReadOnly
    public Optional<Dier> findById(long id) {
        return Optional.ofNullable(entityManager.find(Dier.class, id));
    }

    @Override
    @Transactional
    public Dier save(String ras, String naam, String geslacht) {
        Dier dier = new Dier(ras, naam, geslacht);
        entityManager.persist(dier);
        return dier;
    }

    @Override
    @Transactional
    public Dier saveWithException(String ras, String naam, String geslacht) {
        save(ras, naam, geslacht);
        throw new PersistenceException();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    @Transactional
    public List<Dier> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT g FROM Dier as g";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + ' ' + args.getOrder().get().toLowerCase();
        }
        TypedQuery<Dier> query = entityManager.createQuery(qlString, Dier.class);
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }

    @Override
    @Transactional
    public int update(long id, String name) {
        return entityManager.createQuery("UPDATE Dier g SET g.geslacht = :geslacht where g.dierId = :id")
                .setParameter("geslacht", name)
                .setParameter("id", id)
                .executeUpdate();
    }
}
