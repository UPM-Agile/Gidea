/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package upm.gidea.entities.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import upm.gidea.entities.Entrepreuner;

/**
 *
 * @author user
 */
@Stateless
public class EntrepreunerFacade extends AbstractFacade<Entrepreuner> {
    @PersistenceContext(unitName = "upm.gidea_war_1.0-SNAPSHOT_PU")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public EntrepreunerFacade() {
        super(Entrepreuner.class);
    }
    
}
