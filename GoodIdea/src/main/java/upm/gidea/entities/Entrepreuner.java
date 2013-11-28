/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package upm.gidea.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity(name ="entrepreuner")
public class Entrepreuner implements Serializable {
    private String name;
    private String lastname;
    @OneToMany
    private ArrayList<Idea> listIdea;
    @Id
    private Long entrepeuner_id;

    /**
     *
     * @return
     */
    public Long getEntrepeuner_id() {
        return entrepeuner_id;
    }

    /**
     *
     * @param entrepeuner_id
     */
    public void setEntrepeuner_id(Long entrepeuner_id) {
        this.entrepeuner_id = entrepeuner_id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    public ArrayList<Idea> getListIdea() {
        return listIdea;
    }

    /**
     *
     * @param listIdea
     */
    public void setListIdea(ArrayList<Idea> listIdea) {
        this.listIdea = listIdea;
    }

    /**
     *
     */
    public Entrepreuner() {
    }

    /**
     *
     * @param name
     * @param lastname
     * @param listIdea
     * @param entrepeuner_id
     */
    public Entrepreuner(String name, String lastname, ArrayList<Idea> listIdea, Long entrepeuner_id) {
        this.name = name;
        this.lastname = lastname;
        this.listIdea = listIdea;
        this.entrepeuner_id = entrepeuner_id;
    }

    
}
