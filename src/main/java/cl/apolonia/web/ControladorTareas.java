package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.TareasEjecutadas;
import cl.apolonia.service.ArchivoService;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ObservacionesService;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Descargas
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ControladorTareas {

    @Autowired
    private FuncionariosService funcionariosService;

    @Autowired
    private TareasEjecutadasDao tareasEjecutadasDao;

    @Autowired
    private TareasEjecutadasServices tareasEjecutadasService;

    @Autowired
    private FuncionariosDao funcionariosDao;

    @Autowired
    private procParticipoService procParticipoService;

    @Autowired
    private ProcesosTipoService procesosTipoService;

    @Autowired
    private ProcesosTipoDao procesosTipoDao;

    @Autowired
    private TareasTipoDao tareasTipoDao;

    @Autowired
    private ArchivoService archivoService;

    @Autowired
    private ObservacionesService observacionesService;
    
    @Autowired
    private ServletContext servletContext;

    private final String UPLOAD_DIR = "./uploads/";

    //desde mi flujo de trabajo
    @GetMapping("/gestionartarea")
    public String gestionarTarea(@RequestParam(value = "r") String urlParam,
            @RequestParam(value = "i") Integer i, Model model) {
        var tareasEjecutadas = tareasEjecutadasDao.findByRunResponsableAndIdtarea(urlParam, i);
        var funcionarios = funcionariosService.listarFuncionarios();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        model.addAttribute("run", urlParam);
        model.addAttribute("id", i);
        model.addAttribute("tareasEjecutadas", tareasEjecutadas);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("funcionariosList", funcionariosList);

        return "gestionartarea";
    }

    //Desde gestionar tarea
    @PostMapping("/AceptarTarea")
    public ModelAndView AceptarTarea(@RequestParam(value = "id") Integer urlParam,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar
        if (tareasEjecutadasService.cambiarEstado(tarea, 2) && tareasEjecutadasService.crearObservacion(tarea, runUser, "Aceptada")) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("/gestionarTarea");
        }

    }

    //Desde gestionar tarea
    @PostMapping("/iniciarTarea")
    public ModelAndView IniciarTarea(@RequestParam(value = "id") Integer urlParam,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar 
        if (tareasEjecutadasService.cambiarEstado(tarea, 3) && tareasEjecutadasService.crearObservacion(tarea, runUser, "Iniciada")) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("/gestionarTarea");
        }
    }

    //Desde gestionar tarea CREAR TAREA EJECUTADA + CREAR TAREA EJECUTADA SUBORDINADA
    @GetMapping("/subordinar")
    public ModelAndView subordinar(@RequestParam(value = "idtarea") Integer urlParam,
            @RequestParam(value = "nombreSub") String nombreSub,
            @RequestParam(value = "descripcionSub") String descripcionSub,
            @RequestParam(value = "responsableSub") List<String> responsableSub,
            @RequestParam(value = "idproceso") Integer idproceso,
            @RequestParam(value = "duracionSub") int duracion,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        var runUser = funcionariosService.runResponsable();

        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        TareasEjecutadas subordinada = new TareasEjecutadas(nombreSub, null, idproceso, descripcionSub, runUser);
        if (tareasEjecutadasService.crearTarea(subordinada, duracion, responsableSub, null, tarea.getIdtarea())) {
            return new ModelAndView("redirect:/flujotrabajo");
        }
        return new ModelAndView("/gestionarTarea");
    }

    //Desde gestionar tarea
    @GetMapping("/reportar")
    public ModelAndView reportar(@RequestParam(value = "id") Integer urlParam,
            @RequestParam(value = "descripcion") String descRechazo,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar
        if (tareasEjecutadasService.crearObservacion(tarea, runUser, descRechazo)) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("/gestionarTarea");
        }
    }

    //Desde gestionar tarea
    @PostMapping("/terminarTarea")
    public ModelAndView terminarTarea(@RequestParam(value = "id") Integer urlParam,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar
        if (tareasEjecutadasService.cambiarEstado(tarea, 4)) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("/gestionarTarea");
        }
    }

    @GetMapping("/rechazarTarea")
    public ModelAndView rechazarTarea(@RequestParam(value = "id") Integer urlParam,
            @RequestParam(value = "descRechazo") String descRechazo,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar
        if (tareasEjecutadasService.cambiarEstado(tarea, 5) && tareasEjecutadasService.crearObservacion(tarea, runUser, descRechazo)) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("/gestionarTarea");
        }
    }

    //Nueva tarea a partir de un proceso seleccionado
    @PostMapping("/nuevaTarea")
    public String nuevaTarea(@RequestParam(value = "r") int urlParam,
            @RequestParam(value = "n") String n,
            Model model) {
        //variables

        var r = urlParam;
        var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var tareas = tareasEjecutadasDao.findByIdProcesoEjecutado(r);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        //modelo
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("tareas", tareas);
        model.addAttribute("r", r);
        model.addAttribute("nombreProceso", n);
        model.addAttribute("funcionariosList", funcionariosList);
        return "nuevaTarea";
    }

    //Nueva tarea desde un proceso seleccionado
    @PostMapping("/CrearNuevaTarea")
    public ModelAndView CrearNuevaTarea(
            @RequestParam(value = "nombre") String urlParam,
            @RequestParam(value = "descripcion") String descripcion,
            @RequestParam(value = "responsable", required = false) List<String> responsable,
            @RequestParam(value = "idproceso") int idproceso,
            @RequestParam(value = "fechai") String fechai,
            @RequestParam(value = "duracion") int duracion,
            @RequestParam(value = "dependencia", required = false) List<String> dependencia,
            Model model) throws ParseException {
        //variables
        var nombre = urlParam;
        String runUser = funcionariosService.runResponsable();
        Date d = new SimpleDateFormat("yyyy/MM/dd").parse(fechai);
        TareasEjecutadas tarea = new TareasEjecutadas(nombre, d, idproceso, descripcion, runUser);
        if (tareasEjecutadasService.crearTarea(tarea, duracion, responsable, dependencia)) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("redirect:/nuevaTarea");
        }
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }
        var runUser = funcionariosService.runResponsable();
        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName.lastIndexOf('.'));

        int id = archivoService.create(fileName, runUser, 1);

        if (id > 0) {
            // save the file on the local file system
            try {

                Path path = Paths.get(UPLOAD_DIR + String.valueOf(id));
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // return success response
            attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        }

        return "redirect:/";
    }

    // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @RequestMapping("/download")
    // Hay que traerse el id del archivo y el nombre
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, /*Id archivo*/ "5");
        System.out.println("fileName: " + "CASO 5 PTY4613 2019.pdf");
        System.out.println("mediaType: " + mediaType);

        File file = new File(UPLOAD_DIR + "/" + /*Aca va Id de la tarea*/ "5");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + /*Aca va el nombre del archivo que le entregaremos al usuario*/ "CASO 5 PTY4613 2019.pdf")
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    @GetMapping(value = "/solicitudes")
    public String tareaxproceso(Model model) {
        var idsubunidad = funcionariosService.idSubunidad();
        var rechazadas = tareasEjecutadasService.listarXEstadoXIdSubunidad("Rechazada", idsubunidad);
        var revision = tareasEjecutadasService.listarXEstadoXIdSubunidad("En Revisión", idsubunidad);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("rechazadas", rechazadas);
        model.addAttribute("revision", revision);

        return "solicitudes";
    }

    @GetMapping(value = "/rechazo")
    public String rechazo(@RequestParam(value = "idtarea") Integer urlParam,
            Model model) {
        var idsubunidad = funcionariosService.idSubunidad();
        var funcionarios = funcionariosService.ListarXSubunidad(idsubunidad);
        var tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        var observaciones = observacionesService.ListarXIdtarea(urlParam);
        //variables para saludo superior
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("tarea", tarea);
        model.addAttribute("observaciones", observaciones);
        model.addAttribute("funcionarios", funcionarios);

        return "rechazo";
    }

    @GetMapping(value = "/aceptarechazo")
    public ModelAndView aceptarechazo(@RequestParam(value = "idtarea") Integer urlParam,
            @RequestParam(value = "responsable", required = false) List<String> responsable,
            Model model) {
        //Update a tarea con el id y los nuevos responsables
        //Update a estado a programada

        return new ModelAndView("redirect:/solicitudes");
    }

    @GetMapping(value = "/denegarechazo")
    public ModelAndView denegarechazo(@RequestParam(value = "idtarea") Integer urlParam,
            @RequestParam(value = "motivo") String motivo,
            Model model) {

        //Update estado a aceptada
        return new ModelAndView("redirect:/solicitudes");
    }

    @GetMapping(value = "/revision")
    public String revision(@RequestParam(value = "idtarea") Integer urlParam,
            Model model) {
        var observaciones = observacionesService.ListarXIdtarea(urlParam);
        var tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        var archivo = archivoService.ListarXTarea(urlParam);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("tarea", tarea);
        model.addAttribute("observaciones", observaciones);
        model.addAttribute("archivo", archivo);

        return "revision";
    }

       // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @RequestMapping("/descargar")
    // Hay que traerse el id del archivo y el nombre
    public ResponseEntity<InputStreamResource> descargar(@RequestParam(value="idArchivo") Integer urlParam,
                                                          @RequestParam(value="nombreArchivo") String nombreArchivo) throws IOException {
        String idTarea = urlParam.toString();
        String nombre = nombreArchivo;
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, /*Id archivo*/ idTarea);
        System.out.println("fileName: " + "CASO 5 PTY4613 2019.pdf");
        System.out.println("mediaType: " + mediaType);

        File file = new File(UPLOAD_DIR + "/" + /*Aca va Id de la tarea*/ idTarea);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + /*Aca va el nombre del archivo que le entregaremos al usuario*/ nombre)
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
    
    
        @GetMapping(value = "/apruebarevision")
    public ModelAndView apruebarevision(@RequestParam(value = "idtarea") Integer urlParam,
                                        Model model) {

        //Update estado a aceptada
        return new ModelAndView("redirect:/solicitudes");
    }
    
            @GetMapping(value = "/rechazarevision")
    public ModelAndView rechazarevision(@RequestParam(value = "idtarea") Integer urlParam,
            @RequestParam(value = "motivo") String motivo,
            Model model) {

        //Update estado a aceptada
        return new ModelAndView("redirect:/solicitudes");
    }
    
}
