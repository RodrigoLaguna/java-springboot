package mx.com.gm.service;

import java.util.List;
import mx.com.gm.dao.IPersonaDAO;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements IPersonaService{
        
        // Inyeccion de dependencias @Inject
        @Autowired
        private IPersonaDAO personaDao;

        @Override
        @Transactional(readOnly = true)
        public List<Persona> listarPersonas() {
                return (List<Persona>) personaDao.findAll();
        }

        @Override
        @Transactional
        public void guardar(Persona persona) {
                personaDao.save(persona);
        }

        @Override
        @Transactional
        public void eliminar(Persona persona) {
                personaDao.delete(persona);
        }

        @Override
        @Transactional(readOnly = true)
        public Persona encontrar(Persona persona) {
                return personaDao.findById(persona.getIdPersona()).orElse(null);
        }
        
}
