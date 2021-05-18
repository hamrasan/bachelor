package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.AbstractEntity;

import java.util.Collection;
import java.util.List;

/**
 * Base interface for data access objects.
 *
 * @param <T>
 */
public interface GenericDao<T extends AbstractEntity> {

    /**
     * Finds entity instance with the specified identifier.
     *
     * @param id Identifier
     * @return Entity instance or {@code null} if no such instance exists
     */
    T find(Long id);

    /**
     * Finds all instances of the specified class.
     *
     * @return List of instances, possibly empty
     */
    List<T> findAll();

    /**
     * Persists the specified entity.
     *
     * @param entity Entity to persist
     */
    void persist(T entity);

    /**
     * Persists the specified instances.
     *
     * @param entities Entities to persist
     */
    void persist(Collection<T> entities);

    /**
     * Updates the specified entity.
     *
     * @param entity Entity to update
     * @return The updated instance
     */
    T update(T entity);

    /**
     * Removes the specified entity.
     *
     * @param entity Entity to remove
     */
    void remove(T entity);

    /**
     * Checks whether an entity with the specified id exists (and has the type managed by this DAO).
     *
     * @param id Entity identifier
     * @return {@literal true} if entity exists, {@literal false} otherwise
     */
    boolean exists(Long id);
}