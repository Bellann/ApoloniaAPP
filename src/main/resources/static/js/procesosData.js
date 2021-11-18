   $(document).ready(function() {
    $('#tareasProceso').DataTable({
        "language":{
            "LengthMenu": "Mostrar _MENU_ registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infofiltered": "(Filtrando un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate":{
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "SProcessing": "Procesando...",
        }
    });
} );

  function orderRowClick(row) {
     row.querySelector("a").click();
  }

