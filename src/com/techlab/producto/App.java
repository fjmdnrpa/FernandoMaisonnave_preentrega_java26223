package com.techlab.producto;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.producto.model.Producto;
import com.techlab.producto.model.ProductoDisco;
import com.techlab.producto.model.ProductoLibro;
import com.techlab.producto.model.Categoria;

/*
 * CLASE 4 - HERENCIA, POLIMORFISMO Y TOSTRING
 * --------------------------------------------------
 * OBJETIVO DIDÁCTICO:
 * Hasta la clase anterior, existía una sola clase Articulo.
 *
 * Ahora vamos a modelar una situación más real:
 * no todos los artículos son iguales.
 *
 * Por eso vamos a trabajar con:
 * - una clase abstracta Articulo
 * - una clase ProductoDisco
 * - una clase ProductoLibro
 * ¿Qué queremos enseñar en esta etapa?
 * 1) Qué es una clase abstracta
 * 2) Qué significa heredar
 * 3) Qué es el polimorfismo
 * 4) Cómo una misma lista puede guardar distintos subtipos
 * 5) Qué papel cumple toString()
 * 6) Cómo complementar toString() con métodos específicos
 *
 * IMPORTANTE:
 * En esta etapa todavía NO usamos interfaz ni generics.
 * Primero queremos fijar muy bien herencia y polimorfismo.
 */
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // La lista está declarada como ArrayList<Articulo>.
        // Esto es muy importante.
        // Significa que la lista puede guardar cualquier objeto cuyo tipo sea Articulo
        // o cualquier clase hija de Articulo.
        ArrayList<Producto> productos = new ArrayList<>();

        ArrayList<Categoria> categorias = new ArrayList<>();
        precargarCategorias(categorias);

        int opcion;

        do {
            System.out.println("\n-----------------------------------");
            System.out.println(" SISTEMA DE GESTIÓN DE PRODUCTOS");
            System.out.println("------------------------------------");
            System.out.println("1 - Ingresar producto");
            System.out.println("2 - Listar productos");
            System.out.println("3 - Consultar un producto");
            System.out.println("4 - Modificar un producto");
            System.out.println("5 - Eliminar un producto");
            System.out.println("6 - Listar categorías");
            System.out.println("0 - Salir");
            System.out.println("-------------------------------------");

            opcion = leerEntero(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarProducto(scanner, productos, categorias);
                    break;
                case 2:
                    listarProductos(productos);
                    break;
                case 3:
                    consultarProducto(scanner, productos);
                    break;
                case 4:
                    modificarProducto(scanner, productos, categorias);
                    break;
                case 5:
                    eliminarProducto(scanner, productos);
                    break;
                case 6:
                    listarCategorias(categorias);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    /*
     * MÉTODO: precargarCategorias
     * --------------------------------------------------
     * Seguimos usando categorías precargadas para no sumar todavía
     * el CRUD de categorías.
     */
    public static void precargarCategorias(ArrayList<Categoria> categorias) {
        categorias.add(new Categoria(1, "Literatura", "Novela"));
        categorias.add(new Categoria(2, "Literatura", "Ciencia Ficción"));
        categorias.add(new Categoria(3, "Musica", "Rock"));
        categorias.add(new Categoria(4, "Musica", "Pop"));
        categorias.add(new Categoria(5, "Literatura", "Biografia"));
        categorias.add(new Categoria(6, "Literatura", "Cuentos"));
        categorias.add(new Categoria(7, "Musica", "Tango"));
        categorias.add(new Categoria(8, "Musica", "Jazz"));
    }

    /*
     * MÉTODO: ingresarArticulo
     * --------------------------------------------------
     * Ahora el usuario debe elegir qué tipo de artículo quiere crear.
     *
     * Según la elección:
     * - si es electrónico -> creamos ArticuloElectronico
     * - si es alimenticio -> creamos ArticuloAlimenticio
     *
     * Ambos objetos se guardan en la misma lista ArrayList<Articulo>.
     * Eso es polimorfismo.
     */
    public static void ingresarProducto(
            Scanner scanner,
            ArrayList<Producto> productos,
            ArrayList<Categoria> categorias
    ) {
        System.out.println("\n--- INGRESAR PRODUCTO ---");
        System.out.println("1 - Libro");
        System.out.println("2 - Disco");

        int tipo;
        do {
            tipo = leerEntero(scanner, "Seleccione el tipo de producto: ");

            if (tipo != 1 && tipo != 2) {
                System.out.println("Error: debe elegir 1 o 2.");
            }

        } while (tipo != 1 && tipo != 2);

        int codigo = leerEntero(scanner, "Ingrese el código del producto: ");

        if (buscarProductoPorCodigo(productos, codigo) != null) {
            System.out.println("Error: ya existe un producto con ese código.");
            return;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del producto: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese el precio del producto: ");

        listarCategorias(categorias);
        Categoria categoria = pedirCategoriaExistente(scanner, categorias);

        // Declaramos una variable de tipo Articulo.
        // Después le asignaremos una instancia de la clase hija correspondiente.
        Producto producto;

        if (tipo == 1) {
            int paginas = leerEnteroNoNegativo(scanner, "Ingrese la cantidad de páginas: ");

            // Creamos un objeto de la clase hija ProductoLibro.
            producto = new ProductoLibro(codigo, nombre, precio, categoria, paginas);
        } else {
            int duracion = leerEnteroNoNegativo(scanner, "Ingrese la duración en minutos: ");

            // Creamos un objeto de la clase hija ProductoDisco.
            producto = new ProductoDisco(codigo, nombre, precio, categoria, duracion);
        }

        productos.add(producto);

        System.out.println("Producto ingresado correctamente.");
        System.out.println("Resumen del objeto creado:");
        System.out.println(producto);
    }

    /*
     * MÉTODO: listarProductos
     * --------------------------------------------------
     * Acá aparece una de las grandes ventajas de toString().
     *
     * Aunque la lista guarda distintos subtipos,
     * podemos imprimir cada objeto directamente.
     *
     * Java llamará al toString() del objeto real almacenado.
     */
    public static void listarProductos(ArrayList<Producto> productos) {
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto producto : productos) {
            // Gracias a toString(), mostrar el objeto es muy simple.
            System.out.println(producto);
            if (producto instanceof ProductoDisco) {
                // tengo que transformar producto en ProductoDisco para acceder a su método específico
               // producto.nroTelMesaDeAyudaParaReclamos();
            }
           

            // Además, mostramos un detalle específico usando un método concreto.
            // Esto permite explicar que toString() no reemplaza todo:
            // muchas veces también tendremos métodos específicos del dominio.
           /* System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
            System.out.println("--------------------------------------------");*/ 
        }
    }

    /*
     * MÉTODO: consultarProducto
     * --------------------------------------------------
     * Busca un producto por código y luego lo muestra.
     *
     * También se aprovecha para explicar:
     * - el uso de toString()
     * - la posibilidad de consultar métodos específicos
     */
    public static void consultarProducto(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("\n--- CONSULTAR PRODUCTO ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del producto a consultar: ");

        Producto producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        System.out.println("Producto encontrado:");
        System.out.println(producto);
        System.out.println("Detalle específico: " + producto.getDetalleEspecifico());
    }

    /*
     * MÉTODO: modificarProducto
     * --------------------------------------------------
     * Permite modificar los datos comunes a todos los artículos:
     * - nombre
     * - precio
     * - categoría
     *
     * Y además modifica los datos específicos según el subtipo.
     *
     * Para eso usamos instanceof.
     *
     * instanceof permite preguntar:
     * "¿Este objeto es de este tipo concreto?"
     */
    public static void modificarProducto(
            Scanner scanner,
            ArrayList<Producto> productos,
            ArrayList<Categoria> categorias
    ) {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del producto a modificar: ");

        Producto producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del producto: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese el nuevo precio del producto: ");

        listarCategorias(categorias);
        Categoria nuevaCategoria = pedirCategoriaExistente(scanner, categorias);

        producto.setNombre(nuevoNombre);
        producto.setPrecio(nuevoPrecio);
        producto.setCategoria(nuevaCategoria);

        // Si el artículo real es electrónico, permitimos modificar la garantía.
        if (producto instanceof ProductoDisco) {
            ProductoDisco disco = (ProductoDisco) producto;

            int nuevaDuracion = leerEnteroNoNegativo(scanner, "Ingrese la nueva duracion en minutos: ");
            disco.setDuracionMinutos(nuevaDuracion);
        }

        // Si el artículo real es alimenticio, permitimos modificar los días para vencimiento.
        if (producto instanceof ProductoLibro) {
            ProductoLibro libro = (ProductoLibro) producto;

            int nuevasPaginas = leerEnteroNoNegativo(scanner, "Ingrese la nueva cantidad de páginas: ");
            libro.setPaginas(nuevasPaginas);
        }

        System.out.println("Producto modificado correctamente.");
    }

    /*
     * MÉTODO: eliminarProducto
     * --------------------------------------------------
     * Elimina un producto por código.
     */
    public static void eliminarProducto(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el código del producto a eliminar: ");

        Producto producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("El producto no existe.");
            return;
        }

        productos.remove(producto);
        System.out.println("Producto eliminado correctamente.");
        System.out.println(producto);
    }

    /*
     * MÉTODO: listarCategorias
     * --------------------------------------------------
     * Muestra las categorías disponibles.
     */
    public static void listarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");

        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    /*
     * MÉTODO: pedirCategoriaExistente
     * --------------------------------------------------
     * Obliga al usuario a elegir una categoría válida.
     */
    public static Categoria pedirCategoriaExistente(Scanner scanner, ArrayList<Categoria> categorias) {
        while (true) {
            int codigoCategoria = leerEntero(scanner, "Ingrese el código de la categoría: ");

            Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println("Error: la categoría no existe.");
        }
    }

    /*
     * MÉTODO: buscarProductoPorCodigo
     * --------------------------------------------------
     * Recorre la lista y devuelve el objeto cuyo código coincida.
     */
    public static Producto buscarProductoPorCodigo(ArrayList<Producto> productos, int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /*
     * MÉTODO: buscarCategoriaPorCodigo
     * --------------------------------------------------
     * Recorre la lista de categorías y devuelve la coincidente.
     */
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    /*
     * MÉTODO: leerEntero
     * --------------------------------------------------
     * Lee enteros de forma segura.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    /*
     * MÉTODO: leerEnteroNoNegativo
     * --------------------------------------------------
     * Lee enteros y además valida que no sean negativos.
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);

            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo.");
                continue;
            }

            return valor;
        }
    }

    /*
     * MÉTODO: leerDoubleNoNegativo
     * --------------------------------------------------
     * Lee un número decimal y valida que no sea negativo.
     */
    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    /*
     * MÉTODO: leerTextoNoVacio
     * --------------------------------------------------
     * Obliga a ingresar un texto no vacío.
     */
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.println("Error: el texto no puede estar vacío.");
        }
    }
}
