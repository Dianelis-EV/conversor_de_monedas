package Interfaz;

import Logica.Convertir;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Interfaz {

    public void header(){
        System.out.println("*************************************************************");
        System.out.println(" ");
        System.out.println("Bienvenido al sistema de intercambio de monedas");
        System.out.println(" ");
        System.out.println("*************************************************************");
    }

    public void interfaz_principal(){
        System.out.println("Seleccione la opción deseada:");
        System.out.println("1. Obtener todas las tasas de conversión de una moneda determinada");
        System.out.println("2. Obtener el cambio de dos monedas en específico");
        System.out.println("Otro. Salir");
        String valor = new Scanner(System.in).nextLine();
        switch(valor){
            case "1":
                try {
                    interfazAllMoney();
                    interfaz_principal();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "2":
                try {
                    interfazCambio();
                    interfaz_principal();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Gracias por utilizar nuestra aplicación");
                break;
        }

    }

    public void interfazAllMoney() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        header();
        System.out.println("Indica la moneda a verificar por su siglas");
        String moneda = new Scanner(System.in).nextLine().toUpperCase();
        try {

            for (Map.Entry<String,Double>elemento : new Convertir().listado_monedas(moneda).entrySet()){
                System.out.println(elemento.getKey() + "-" + new Convertir().clave_de_monedads().get(elemento.getKey())+"-"+ elemento.getValue());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Compruebe su conexión a Internet");
        }
    }

    public void interfazCambio()throws Exception{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        header();
        System.out.println("Indica las 2 monedas y su valor a verificar por su siglas");
        try {
            Map<String,String> monedas = new Convertir().clave_de_monedads();
            for (Map.Entry<String,String>elemento : monedas.entrySet()){
                System.out.println(elemento.getKey() + "-" + elemento.getValue());
            }
            System.out.println("Indica la primera moneda");
            String moneda = new Scanner(System.in).nextLine().toUpperCase();
            System.out.println("Indica la segunda moneda");
            String moneda1 = new Scanner(System.in).nextLine().toUpperCase();
            System.out.println("Indica la cantidad de "+moneda+" que desea convertir a "+moneda1);
            Double valor = new Scanner(System.in).nextDouble();
            System.out.println(valor+"-"+moneda+" = "+new Convertir().cambiode2monedas(moneda,moneda1,valor)+"-"+moneda1);
        }catch (Exception e){
            System.out.println("Compruebe su internet o su ingreso de datos");
        }
    }
}
