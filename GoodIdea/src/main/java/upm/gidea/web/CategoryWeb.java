/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package upm.gidea.web;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
*/
 @XmlRootElement
public class CategoryWeb {
     private String name;

    public CategoryWeb() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     
    
}
