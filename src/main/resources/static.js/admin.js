                                function cargarFunciones() {
                                $('#funciones').prop('disabled', false);
                                var productoId = document.getElementById("select-producto").value;
                                if (productoId) {
                                $.ajax({
                                url: '/productos/' + productoId + '/funciones',
                                type: 'GET',
                                success: function(data) {
                                $('#funciones').html(data);
                                $('#funciones').prop('disabled', false);
                                },
                                error: function() {
                                console.log('Error al obtener las funciones del producto');
                                }
                                });
                                } else {
                                $('#funciones').html('<option value="">Selecciona un producto primero</option>');
                                $('#funciones').prop('disabled', true);
                                }
                                }
