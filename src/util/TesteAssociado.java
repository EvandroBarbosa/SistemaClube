/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entidade.EAssociado;
import Entidade.ETipoAssociado;
import Persistencia.PAssociado;
import java.util.Scanner;

/**
 *
 * @author Evandro
 */
public class TesteAssociado {
    public static void main(String[] args) {
//        //criando um objeto de associado
//        EAssociado socio = new EAssociado();
//        //criando uma persistencia para gravar no banco
//        PAssociado pa = new PAssociado();
//        
//        socio.setCodigo(1);
//        socio.setNome("Maria");
//        socio.setEndereco("Rua dos Perdidos");
//        socio.getTipoAssociado().setCodigo(3);
//        try {
//            pa.incluir(socio);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        //criando um objeto de associado
        EAssociado socio = new EAssociado();
        //criando uma persistencia para gravar no banco
        PAssociado pa = new PAssociado();
        Scanner entrada = new Scanner(System.in);
        
        
        try {
            System.out.print("Codigo....................:");
            socio.setCodigo(entrada.nextInt());
            System.out.print("Nome......................:");
            socio.setNome(entrada.next());
            System.out.print("Endereco..................:");
            socio.setEndereco(entrada.next());
            System.out.print("Codigo tipoAssociado......:"); 
            socio.getTipoAssociado().setCodigo(entrada.nextInt());
            
            pa.alterar(socio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
//        //criando uma persistencia para gravar no banco
//        PAssociado pa = new PAssociado();       
//        
//        try {
//            
//            for (EAssociado socio : pa.listar()) {
//                System.out.println("Codigo....................:"+socio.getCodigo());
//                System.out.println("Nome......................:"+socio.getNome());
//                System.out.println("Endereco..................:"+socio.getEndereco());
//                System.out.println("Codigo tipoAssociado......:"+socio.getTipoAssociado().getCodigo()); 
//                System.out.println("");
//                
//            }
//            
//           
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
