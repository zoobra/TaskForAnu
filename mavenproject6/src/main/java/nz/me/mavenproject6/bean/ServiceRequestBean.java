package nz.me.mavenproject6.bean;


import nz.me.mavenproject6.model.ServiceRequest;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import javax.persistence.*;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
import java.io.Serializable;
//import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class ServiceRequestBean implements Serializable {

    private List<ServiceRequest> requests;
    private ServiceRequest newRequest = new ServiceRequest();
    private ServiceRequest selectedRequest;
    
    private final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("support_tracker_pu");

    @PostConstruct
    public void init() {
        loadRequests();
    }

    private void loadRequests() {
        EntityManager em = emf.createEntityManager();
        requests = em.createQuery("SELECT r FROM ServiceRequest r", ServiceRequest.class)
                    .getResultList();
        em.close();
    }

    public void addRequest() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //newRequest.setDateSubmitted(new Date());
            em.persist(newRequest);
            em.getTransaction().commit();
            
            newRequest = new ServiceRequest();
            loadRequests();
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Request Added", null));
        } finally {
            em.close();
        }
    }

    public void updateRequest() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(selectedRequest);
            em.getTransaction().commit();
            loadRequests();
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Request Updated", null));
        } finally {
            em.close();
        }
    }

    public void deleteRequest() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ServiceRequest request = em.find(ServiceRequest.class, selectedRequest.getId());
            if (request != null) {
                em.remove(request);
            }
            em.getTransaction().commit();
            loadRequests();
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Request Deleted", null));
        } finally {
            em.close();
        }
    }

    // Getters and Setters
    public List<ServiceRequest> getRequests() { return requests; }
    public ServiceRequest getNewRequest() { return newRequest; }
    public ServiceRequest getSelectedRequest() { return selectedRequest; }
    public void setSelectedRequest(ServiceRequest selectedRequest) { 
        this.selectedRequest = selectedRequest; 
    }
}