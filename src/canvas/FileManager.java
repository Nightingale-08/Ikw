package canvas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter,fileWriter2;
    private BufferedWriter output,output2;
    private int nivel;
    public  static boolean  registrado;
    private List<String> listaPalabras;
    private List<String> palabrasEscogidas;
    private List<String> lista;
    private List<Integer> listaUsuarios;
    private static final int serie = 2;
    private String usuarioPrincipal,palabra;
    private FileReader fileRead,usuariosRead,nivelUsuariosRead;
    private BufferedReader input2,input3;

    private String PATCH_Usuarios= "src/canvas/txt/usu.txt";
    private String PATCH_Word= "src/canvas/txt/word.txt";




    public FileManager() {
        try {
            listaPalabras = new ArrayList<String>();
            palabrasEscogidas = new ArrayList<String>();
            lista = new ArrayList<String>();
            usuariosRead = new FileReader(PATCH_Usuarios);
            input = new BufferedReader(usuariosRead);
            String texto = null;
            int number = 0;
            texto = input.readLine();
            while(texto!=null) {
                lista.add(texto);
                texto = input.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String>lecturaFile() {
        ArrayList <String>frases=new ArrayList<>();

        try {
            fileReader = new FileReader(PATCH_Word);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                frases.add(line);

                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return frases;
    }

    public ArrayList<String>lecturaUsuario() {
        ArrayList <String>Nombres=new ArrayList<>();

        try {
            fileReader = new FileReader(PATCH_Usuarios);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                Nombres.add(line);
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Nombres;
    }


    public void escribirUsuario(String linea) throws IOException {
        ArrayList<String> nombreUsuario= new ArrayList<String>();
        nombreUsuario=lecturaUsuario();


        //   Boolean var = nombreUsuario.contains(linea);
        String usuarioBuscado="";
        for (int i = 0; i < nombreUsuario.size(); i++) {
            String thisUser = nombreUsuario.get(i).substring(0, nombreUsuario.get(i).lastIndexOf(";"));
            if (thisUser.equals(linea)) {

                usuarioBuscado= thisUser;
                break;
            }

        }
        System.out.println(usuarioBuscado);
        System.out.println(linea);
        if(usuarioBuscado.equals(linea)){

        }else{
            try {

                fileWriter = new FileWriter(PATCH_Usuarios,true);
                output = new BufferedWriter(fileWriter);
                output.write(linea+";"+1);
                output.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public int buscarUsuario() {

        ArrayList<String> nombreUsuario = new ArrayList<String>();

        int posicion = 0;

        for (int i = 0; i < nombreUsuario.size(); i++) {
            String thisUser = nombreUsuario.get(i).substring(0, nombreUsuario.get(i).lastIndexOf(";"));
            if (thisUser.equals(nombreUsuario)) {
                posicion = i;
                break;
            }

        }
        return posicion;
    }

    public int buscarNivelUsuario(String nombreUsuarioRecibido){

        ArrayList<String> nombrseUsuarios= new ArrayList<String>();
        nombrseUsuarios=lecturaUsuario();

        String nivelBuscado="";
        for (int i = 0; i < nombrseUsuarios.size(); i++) {
            String thisUser = nombrseUsuarios.get(i).substring(0, nombrseUsuarios.get(i).lastIndexOf(";"));
            if (thisUser.equals(nombreUsuarioRecibido)) {

                nivelBuscado= nombrseUsuarios.get(i).substring(nombrseUsuarios.get(i).lastIndexOf(";")+1);
                break;
            }

        }

        return Integer.parseInt(nivelBuscado);

    }

    public void subirNivel() throws IOException {

        if(nivel==5){
        }else{
            ArrayList<String> nombreUsuario= new ArrayList<String>();
            nombreUsuario=lecturaUsuario();


            try {
                fileWriter2 = new FileWriter(PATCH_Usuarios,false);
                output2= new BufferedWriter(fileWriter2);
                output2.write("");
                output2.close();
                for(int i=0;i<lista.size();i++){
                    fileWriter = new FileWriter(PATCH_Usuarios,true);
                    output= new BufferedWriter(fileWriter);

                    if(lista.get(i).substring(0, nombreUsuario.get(i).lastIndexOf(";")).equalsIgnoreCase(GUI.getNombreUsario())){
                        String nombreUsu=lista.get(i).substring(0, nombreUsuario.get(i).lastIndexOf(";"));
                        nivel=Integer.parseInt(lista.get(i).substring(nombreUsuario.get(i).lastIndexOf(";")+1));
                        nivel=nivel+1;
                        lista.set(i, nombreUsu+";"+nivel);
                    }
                    output.write(String.valueOf(lista.get(i)));
                    output.newLine();
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}