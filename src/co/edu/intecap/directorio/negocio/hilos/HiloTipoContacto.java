/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.directorio.negocio.hilos;

import co.edu.intecap.directorio.persistencia.conexion.ConexionBD;
import co.edu.intecap.directorio.persistencia.dao.TipocontactoDao;
import co.edu.intecap.directorio.persistencia.vo.Contacto;
import co.edu.intecap.directorio.persistencia.vo.TipoContacto;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author Capacitaciones1
 */
public class HiloTipoContacto extends Thread {

    private JComboBox<String> cboTipoContacto;
    private List<TipoContacto> listaContacto;
    int seleccionado;

    public HiloTipoContacto(JComboBox<String> cboTipoContacto, List<TipoContacto> listaContacto) {
        this.cboTipoContacto = cboTipoContacto;
        this.listaContacto = listaContacto;
    }

    @Override
    public void run() {
        try {
            while (true) {
                seleccionado=cboTipoContacto.getSelectedIndex();
                listaContacto = new TipocontactoDao(ConexionBD.conectar()).consultar();
                cboTipoContacto.removeAll();
                DefaultComboBoxModel<String> modeloComobo = new DefaultComboBoxModel<>();
                modeloComobo.addElement("seleccione un tipo de contacto");
                for (TipoContacto tc : listaContacto) {
                    modeloComobo.addElement(tc.getNombre());
                }
                cboTipoContacto.setModel(modeloComobo);
                cboTipoContacto.setSelectedIndex(seleccionado);
                Thread.sleep(5000);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
