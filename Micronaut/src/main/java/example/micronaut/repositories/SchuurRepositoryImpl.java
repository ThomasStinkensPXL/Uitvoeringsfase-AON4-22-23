package example.micronaut.repositories;

import example.micronaut.ApplicationConfiguration;
import example.micronaut.SortingAndOrderArguments;
import example.micronaut.commands.DierSaveRequest;
import example.micronaut.domain.Dier;
import example.micronaut.domain.Schuur;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Singleton
public class SchuurRepositoryImpl implements SchuurRepository{
    private static final List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "naamSchuur");

    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;
    private DierRepository dierRepository;

    public SchuurRepositoryImpl(EntityManager entityManager,
                                ApplicationConfiguration applicationConfiguration, DierRepository dierRepository) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
        this.dierRepository = dierRepository;
    }

    @Override
    @ReadOnly
    public Optional<Schuur> findById(long id) {
        return Optional.ofNullable(entityManager.find(Schuur.class, id));
    }

    @Override
    @Transactional
    public Schuur save(String naamSchuur) {
        Schuur schuur = new Schuur(naamSchuur);
        entityManager.persist(schuur);
        return schuur;
    }

    @Override
    @Transactional
    public void addDier(String naamSchuur, DierSaveRequest dierSaveRequest) {
        var query = entityManager.createQuery("SELECT schuur FROM Schuur schuur where schuur.naamSchuur = :schuur").setParameter("schuur", naamSchuur);
        Schuur schuur = (Schuur) query.getSingleResult();

        Dier dier = dierRepository.save(dierSaveRequest.getRas(), dierSaveRequest.getNaam(), dierSaveRequest.getGeslacht());

        schuur.getDieren().add(dier);
    }

    @Override
    @Transactional
    public List<Dier> getDieren(String naamSchuur) {
        var query = entityManager.createQuery("SELECT schuur FROM Schuur schuur where schuur.naamSchuur = :schuur").setParameter("schuur", naamSchuur);
        Schuur schuur = (Schuur) query.getSingleResult();

        return schuur.getDieren();
    }

    @Override
    @Transactional
    public Schuur saveWithException(String naamSchuur) {
        save(naamSchuur);
        throw new PersistenceException();
    }

    @Override
    public void deleteById(long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    @Transactional
    public List<Schuur> findAll(@NotNull SortingAndOrderArguments args) {
//        String qlString = "SELECT schuur FROM Schuur as schuur";
//        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
//            qlString += " ORDER BY g." + args.getSort().get() + ' ' + args.getOrder().get().toLowerCase();
//        }
//        TypedQuery<Schuur> query = entityManager.createQuery(qlString, Schuur.class);
//        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
//        args.getOffset().ifPresent(query::setFirstResult);

//        return query.getResultList();
          var query = entityManager.createQuery("SELECT schuur FROM Schuur schuur");
          return (List<Schuur>) query.getResultList();
    }

    @Override
    public int update(long id, String name) {
        return entityManager.createQuery("UPDATE Schuur g SET naamSchuur = :name where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteAnimalFromBarn(long dierId) {
        List<Schuur> schuren = findAll(new SortingAndOrderArguments(null, null, null, null));
        for (Schuur schuur : schuren) {
            System.out.println(schuur.getDieren());
            schuur.getDieren().removeIf((dier -> dier.getDierId() == dierId));
            entityManager.persist(schuur);
            System.out.println(schuur.getDieren());
        }
    }


}
