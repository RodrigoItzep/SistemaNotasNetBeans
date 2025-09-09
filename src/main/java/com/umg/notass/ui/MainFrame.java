package com.umg.notass.ui;

import com.umg.notass.model.Estudiante;
import com.umg.notass.model.Curso;
import com.umg.notass.model.Grado;
import com.umg.notass.service.EstudianteService;
import com.umg.notass.service.CursoService;
import com.umg.notass.service.GradoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    // Componentes para Estudiante
    private JTable estudianteTable;
    private DefaultTableModel estudianteTableModel;
    private JTextField txtEstudianteId, txtEstudianteNombre, txtEstudianteApellido, txtEstudianteCorreo, txtEstudianteCarnet;
    private JButton btnEstudianteListar, btnEstudianteGuardar, btnEstudianteActualizar, btnEstudianteEliminar;
    private int selectedEstudianteId = -1;

    // Componentes para Curso
    private JTable cursoTable;
    private DefaultTableModel cursoTableModel;
    private JTextField txtCursoId, txtCursoNombre, txtCursoCodigo, txtCursoSemestre;
    private JButton btnCursoListar, btnCursoGuardar, btnCursoActualizar, btnCursoEliminar;
    private int selectedCursoId = -1;

    // Componentes para Grado
    private JTable gradoTable;
    private DefaultTableModel gradoTableModel;
    private JTextField txtGradoId, txtGradoCursoId, txtGradoNota, txtGradoFecha;
    private JComboBox<String> comboGradoTipoEvaluacion;
    private JButton btnGradoListar, btnGradoGuardar, btnGradoActualizar, btnGradoEliminar;
    private int selectedGradoId = -1;

    public MainFrame() {
        setTitle("Sistema de Notas");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña de Estudiantes
        JPanel estudiantePanel = createEstudiantePanel();
        tabbedPane.addTab("Estudiantes", estudiantePanel);

        // Pestaña de Cursos
        JPanel cursoPanel = createCursoPanel();
        tabbedPane.addTab("Cursos", cursoPanel);

        // Pestaña de Grados
        JPanel gradoPanel = createGradoPanel();
        tabbedPane.addTab("Grados", gradoPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createEstudiantePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla
        estudianteTableModel = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Apellido", "Correo", "Carnet"}, 0
        );
        estudianteTable = new JTable(estudianteTableModel);
        JScrollPane scrollPane = new JScrollPane(estudianteTable);

        estudianteTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int row = estudianteTable.getSelectedRow();
            if (row >= 0) {
                selectedEstudianteId = (int) estudianteTableModel.getValueAt(row, 0);
                txtEstudianteId.setText(String.valueOf(selectedEstudianteId));
                txtEstudianteNombre.setText((String) estudianteTableModel.getValueAt(row, 1));
                txtEstudianteApellido.setText((String) estudianteTableModel.getValueAt(row, 2));
                txtEstudianteCorreo.setText((String) estudianteTableModel.getValueAt(row, 3));
                txtEstudianteCarnet.setText((String) estudianteTableModel.getValueAt(row, 4));
            }
        });

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        formPanel.add(new JLabel("ID:"));
        txtEstudianteId = new JTextField();
        txtEstudianteId.setEditable(false);
        formPanel.add(txtEstudianteId);

        formPanel.add(new JLabel("Nombre:"));
        txtEstudianteNombre = new JTextField();
        formPanel.add(txtEstudianteNombre);

        formPanel.add(new JLabel("Apellido:"));
        txtEstudianteApellido = new JTextField();
        formPanel.add(txtEstudianteApellido);

        formPanel.add(new JLabel("Correo:"));
        txtEstudianteCorreo = new JTextField();
        formPanel.add(txtEstudianteCorreo);

        formPanel.add(new JLabel("Carnet:"));
        txtEstudianteCarnet = new JTextField();
        formPanel.add(txtEstudianteCarnet);

        // Botones
        JPanel buttonPanel = new JPanel();
        btnEstudianteListar = new JButton("Consultar");
        btnEstudianteGuardar = new JButton("Guardar");
        btnEstudianteActualizar = new JButton("Actualizar");
        btnEstudianteEliminar = new JButton("Eliminar");

        buttonPanel.add(btnEstudianteListar);
        buttonPanel.add(btnEstudianteGuardar);
        buttonPanel.add(btnEstudianteActualizar);
        buttonPanel.add(btnEstudianteEliminar);

        btnEstudianteListar.addActionListener(e -> listarEstudiantes());
        btnEstudianteGuardar.addActionListener(e -> guardarEstudiante());
        btnEstudianteActualizar.addActionListener(e -> actualizarEstudiante());
        btnEstudianteEliminar.addActionListener(e -> eliminarEstudiante());

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCursoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla
        cursoTableModel = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Código", "Semestre"}, 0
        );
        cursoTable = new JTable(cursoTableModel);
        JScrollPane scrollPane = new JScrollPane(cursoTable);

        cursoTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int row = cursoTable.getSelectedRow();
            if (row >= 0) {
                selectedCursoId = (int) cursoTableModel.getValueAt(row, 0);
                txtCursoId.setText(String.valueOf(selectedCursoId));
                txtCursoNombre.setText((String) cursoTableModel.getValueAt(row, 1));
                txtCursoCodigo.setText((String) cursoTableModel.getValueAt(row, 2));
                txtCursoSemestre.setText(String.valueOf(cursoTableModel.getValueAt(row, 3)));
            }
        });

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Curso"));

        formPanel.add(new JLabel("ID:"));
        txtCursoId = new JTextField();
        txtCursoId.setEditable(false);
        formPanel.add(txtCursoId);

        formPanel.add(new JLabel("Nombre:"));
        txtCursoNombre = new JTextField();
        formPanel.add(txtCursoNombre);

        formPanel.add(new JLabel("Código:"));
        txtCursoCodigo = new JTextField();
        formPanel.add(txtCursoCodigo);

        formPanel.add(new JLabel("Semestre:"));
        txtCursoSemestre = new JTextField();
        formPanel.add(txtCursoSemestre);

        // Botones
        JPanel buttonPanel = new JPanel();
        btnCursoListar = new JButton("Consultar");
        btnCursoGuardar = new JButton("Guardar");
        btnCursoActualizar = new JButton("Actualizar");
        btnCursoEliminar = new JButton("Eliminar");

        buttonPanel.add(btnCursoListar);
        buttonPanel.add(btnCursoGuardar);
        buttonPanel.add(btnCursoActualizar);
        buttonPanel.add(btnCursoEliminar);

        btnCursoListar.addActionListener(e -> listarCursos());
        btnCursoGuardar.addActionListener(e -> guardarCurso());
        btnCursoActualizar.addActionListener(e -> actualizarCurso());
        btnCursoEliminar.addActionListener(e -> eliminarCurso());

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createGradoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tabla
        gradoTableModel = new DefaultTableModel(
                new Object[]{"ID", "Curso ID", "Nota", "Tipo Evaluación", "Fecha"}, 0
        );
        gradoTable = new JTable(gradoTableModel);
        JScrollPane scrollPane = new JScrollPane(gradoTable);

        gradoTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int row = gradoTable.getSelectedRow();
            if (row >= 0) {
                selectedGradoId = (int) gradoTableModel.getValueAt(row, 0);
                txtGradoId.setText(String.valueOf(selectedGradoId));
                txtGradoCursoId.setText(String.valueOf(gradoTableModel.getValueAt(row, 1)));
                txtGradoNota.setText(String.valueOf(gradoTableModel.getValueAt(row, 2)));
                comboGradoTipoEvaluacion.setSelectedItem((String) gradoTableModel.getValueAt(row, 3));
                txtGradoFecha.setText((String) gradoTableModel.getValueAt(row, 4));
            }
        });

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Grado"));

        formPanel.add(new JLabel("ID:"));
        txtGradoId = new JTextField();
        txtGradoId.setEditable(false);
        formPanel.add(txtGradoId);

        formPanel.add(new JLabel("Curso ID:"));
        txtGradoCursoId = new JTextField();
        formPanel.add(txtGradoCursoId);

        formPanel.add(new JLabel("Nota:"));
        txtGradoNota = new JTextField();
        formPanel.add(txtGradoNota);

        formPanel.add(new JLabel("Tipo Evaluación:"));
        comboGradoTipoEvaluacion = new JComboBox<>(new String[]{"parcial", "final", "extraordinario"});
        formPanel.add(comboGradoTipoEvaluacion);

        formPanel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtGradoFecha = new JTextField();
        formPanel.add(txtGradoFecha);

        // Botones
        JPanel buttonPanel = new JPanel();
        btnGradoListar = new JButton("Consultar");
        btnGradoGuardar = new JButton("Guardar");
        btnGradoActualizar = new JButton("Actualizar");
        btnGradoEliminar = new JButton("Eliminar");

        buttonPanel.add(btnGradoListar);
        buttonPanel.add(btnGradoGuardar);
        buttonPanel.add(btnGradoActualizar);
        buttonPanel.add(btnGradoEliminar);

        btnGradoListar.addActionListener(e -> listarGrados());
        btnGradoGuardar.addActionListener(e -> guardarGrado());
        btnGradoActualizar.addActionListener(e -> actualizarGrado());
        btnGradoEliminar.addActionListener(e -> eliminarGrado());

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    // Métodos para Estudiante
    private void listarEstudiantes() {
        try {
            EstudianteService service = new EstudianteService();
            java.util.List<Estudiante> estudiantes = service.getEstudiantes();
            estudianteTableModel.setRowCount(0);
            for (Estudiante e : estudiantes) {
                estudianteTableModel.addRow(new Object[]{
                        e.getId(), e.getNombre(), e.getApellido(), e.getCorreo(), e.getCarnet()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar estudiantes: " + ex.getMessage());
        }
    }

    private void guardarEstudiante() {
        try {
            Estudiante e = new Estudiante();
            e.setNombre(txtEstudianteNombre.getText());
            e.setApellido(txtEstudianteApellido.getText());
            e.setCorreo(txtEstudianteCorreo.getText());
            e.setCarnet(txtEstudianteCarnet.getText());

            EstudianteService service = new EstudianteService();
            service.createEstudiante(e);
            listarEstudiantes();
            JOptionPane.showMessageDialog(this, "Estudiante guardado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar estudiante: " + ex.getMessage());
        }
    }

    private void actualizarEstudiante() {
        if (selectedEstudianteId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para actualizar");
            return;
        }
        try {
            Estudiante e = new Estudiante();
            e.setNombre(txtEstudianteNombre.getText());
            e.setApellido(txtEstudianteApellido.getText());
            e.setCorreo(txtEstudianteCorreo.getText());
            e.setCarnet(txtEstudianteCarnet.getText());

            EstudianteService service = new EstudianteService();
            service.updateEstudiante(selectedEstudianteId, e);
            listarEstudiantes();
            JOptionPane.showMessageDialog(this, "Estudiante actualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar estudiante: " + ex.getMessage());
        }
    }

    private void eliminarEstudiante() {
        if (selectedEstudianteId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para eliminar");
            return;
        }
        try {
            EstudianteService service = new EstudianteService();
            service.deleteEstudiante(selectedEstudianteId);
            listarEstudiantes();
            JOptionPane.showMessageDialog(this, "Estudiante eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar estudiante: " + ex.getMessage());
        }
    }

    // Métodos para Curso
    private void listarCursos() {
        try {
            CursoService service = new CursoService();
            java.util.List<Curso> cursos = service.getCursos();
            cursoTableModel.setRowCount(0);
            for (Curso c : cursos) {
                cursoTableModel.addRow(new Object[]{
                        c.getId(), c.getNombre(), c.getCodigo(), c.getSemestre()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar cursos: " + ex.getMessage());
        }
    }

    private void guardarCurso() {
        try {
            Curso c = new Curso();
            c.setNombre(txtCursoNombre.getText());
            c.setCodigo(txtCursoCodigo.getText());
            c.setSemestre(Integer.parseInt(txtCursoSemestre.getText()));

            CursoService service = new CursoService();
            service.createCurso(c);
            listarCursos();
            JOptionPane.showMessageDialog(this, "Curso guardado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar curso: " + ex.getMessage());
        }
    }

    private void actualizarCurso() {
        if (selectedCursoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para actualizar");
            return;
        }
        try {
            Curso c = new Curso();
            c.setNombre(txtCursoNombre.getText());
            c.setCodigo(txtCursoCodigo.getText());
            c.setSemestre(Integer.parseInt(txtCursoSemestre.getText()));

            CursoService service = new CursoService();
            service.updateCurso(selectedCursoId, c);
            listarCursos();
            JOptionPane.showMessageDialog(this, "Curso actualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar curso: " + ex.getMessage());
        }
    }

    private void eliminarCurso() {
        if (selectedCursoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para eliminar");
            return;
        }
        try {
            CursoService service = new CursoService();
            service.deleteCurso(selectedCursoId);
            listarCursos();
            JOptionPane.showMessageDialog(this, "Curso eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar curso: " + ex.getMessage());
        }
    }

    // Métodos para Grado
    private void listarGrados() {
        try {
            GradoService service = new GradoService();
            java.util.List<Grado> grados = service.getGrados();
            gradoTableModel.setRowCount(0);
            for (Grado g : grados) {
                gradoTableModel.addRow(new Object[]{
                        g.getId(), g.getCursoId(), g.getNota(), g.getTipoEvaluacion(), g.getFecha()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar grados: " + ex.getMessage());
        }
    }

    private void guardarGrado() {
        try {
            Grado g = new Grado();
            g.setCursoId(Integer.parseInt(txtGradoCursoId.getText()));
            g.setNota(Float.parseFloat(txtGradoNota.getText()));
            g.setTipoEvaluacion((String) comboGradoTipoEvaluacion.getSelectedItem());
            g.setFecha(txtGradoFecha.getText());

            GradoService service = new GradoService();
            service.createGrado(g);
            listarGrados();
            JOptionPane.showMessageDialog(this, "Grado guardado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar grado: " + ex.getMessage());
        }
    }

    private void actualizarGrado() {
        if (selectedGradoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un grado para actualizar");
            return;
        }
        try {
            Grado g = new Grado();
            g.setCursoId(Integer.parseInt(txtGradoCursoId.getText()));
            g.setNota(Float.parseFloat(txtGradoNota.getText()));
            g.setTipoEvaluacion((String) comboGradoTipoEvaluacion.getSelectedItem());
            g.setFecha(txtGradoFecha.getText());

            GradoService service = new GradoService();
            service.updateGrado(selectedGradoId, g);
            listarGrados();
            JOptionPane.showMessageDialog(this, "Grado actualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar grado: " + ex.getMessage());
        }
    }

    private void eliminarGrado() {
        if (selectedGradoId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un grado para eliminar");
            return;
        }
        try {
            GradoService service = new GradoService();
            service.deleteGrado(selectedGradoId);
            listarGrados();
            JOptionPane.showMessageDialog(this, "Grado eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar grado: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}