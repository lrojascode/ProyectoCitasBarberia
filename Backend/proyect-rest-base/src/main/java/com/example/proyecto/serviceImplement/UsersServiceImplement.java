package com.example.proyecto.serviceImplement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.proyecto.model.users;
import com.example.proyecto.repository.UsersRepository;
import com.example.proyecto.service.UsersService;

@Service
public class UsersServiceImplement implements UsersService {

    @Autowired
    private UsersRepository dao;

    @Override
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        Map<String, Object> respuesta = new HashMap<>();
        List<users> usuarios = dao.findAll();  //

        if (!usuarios.isEmpty()) {
            respuesta.put("mensaje", "Lista de usuarios");
            respuesta.put("usuarios", usuarios);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "No existen registros");
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> listarUsuariosPorId(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<users> usuario = dao.findById(id);

        if (usuario.isPresent()) {
            respuesta.put("usuario", usuario);
            respuesta.put("mensaje", "Busqueda correcta");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registro con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> agregarUsuarios(users user) {
        Map<String, Object> respuesta = new HashMap<>();
        dao.save(user);
        respuesta.put("usuario", user);
        respuesta.put("mensaje", "Se añadió correctamente el usuario");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Override
    public ResponseEntity<Map<String, Object>> editarUsuarios(users user, Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<users> usuarioExiste = dao.findById(id);

        if (usuarioExiste.isPresent()) {
            users usuario = usuarioExiste.get();
            usuario.setUsername(user.getUsername());
            usuario.setPassword(user.getPassword());
            usuario.setEmail(user.getEmail());
            usuario.setRole_id(user.getRole_id());
            dao.save(usuario);
            respuesta.put("usuario", usuario);
            respuesta.put("mensaje", "Se editó correctamente el usuario");
            respuesta.put("status", HttpStatus.CREATED);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registro con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> eliminarUsuarios(Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<users> usuarioExiste = dao.findById(id);

        if (usuarioExiste.isPresent()) {
            dao.deleteById(id);  //
            respuesta.put("mensaje", "Eliminado correctamente");
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.put("mensaje", "Sin registro con ID: " + id);
            respuesta.put("status", HttpStatus.NOT_FOUND);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }
}
