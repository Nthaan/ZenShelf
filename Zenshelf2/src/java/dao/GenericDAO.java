/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import java.util.List;

public interface GenericDAO<T> {
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
    void save(T object) throws Exception;
    void update(T object) throws Exception;
    void delete(int id) throws Exception;
}
