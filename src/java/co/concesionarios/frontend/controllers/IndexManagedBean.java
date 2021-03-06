
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.concesionarios.frontend.controllers;

import co.concesionario.backend.persistences.entities.Concesionario;
import co.concesionario.backend.persistences.facades.ConcesionarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author camila
 */
@Named(value = "indexManagedBean")
@ViewScoped
public class IndexManagedBean implements Serializable {

    private Concesionario concesionario;
    @EJB
    private ConcesionarioFacadeLocal concesionariofl;
   
    public IndexManagedBean() {
    }
    
    @PostConstruct 
    public void init(){
    
        concesionario = new Concesionario();
        
        
        }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
                
          public String iniciarSesion(){
        Concesionario con;
        String redireccion = null;
        
        try {
           con = concesionariofl.iniciarSesion(concesionario);
            if (con != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("concesionario",con);
            redireccion = "lista_vehiculos";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Valores Incorrectos"));

            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error"));
        }
    return redireccion;
    
          }
          
          public void verificarSesion(){
          
              try {
                  FacesContext context = FacesContext.getCurrentInstance();
                  
                  Concesionario con = (Concesionario)context.getExternalContext().getSessionMap().get("concesionario");
                if (con == null)
                {context.getExternalContext().redirect("permisos.xhtml");}
                  
              } catch (Exception e) {
              }
          
          }
    
          public void cerrarSesion(){
          
             
              FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
          
          }
    }

