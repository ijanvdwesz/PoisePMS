package poisePMS;

/**
 * The {@code PMSInterface} interface defines the common operations for managing
 * various entities within the Poise Project Management System (PMS). It
 * includes methods for adding, updating, deleting, and displaying entities.
 * 
 * @param <T> The type of the entity to be managed.
 * @version 1.0
 * @since 2024-08-12
 */
public interface PMSInterface<T> {
  /**
   * Adds a new entity to the system.
   * 
   * @param entity The entity to be added.
   */
  void add(T entity);

  /**
   * Updates an existing entity in the system.
   * 
   * @param id     The ID of the entity to be updated.
   * @param entity The updated entity.
   */
  void update(int id, T entity);

  /**
   * Deletes an entity from the system.
   * 
   * @param id The ID of the entity to be deleted.
   */
  void delete(int id);

  /**
   * Displays all entities in the system.
   */
  void displayAll();
}