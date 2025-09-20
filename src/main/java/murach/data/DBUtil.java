/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author ADMIN
 */
public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("userAdminPU");
    public  static EntityManagerFactory getEmFactory(){
        return emf;
    }
}
