/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocerimagen;

/**
 *
 * @author Diego C
 */
import java.awt.image.BufferedImage;
import org.neuroph.core.NeuralNetwork;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.neuroph.imgrec.ImageRecognitionPlugin;

public class ImageRecognition {

    public String ObtenOptimo(double muestras[]) {
        double mayor = muestras[0];
        for (int i = 1; i < muestras.length; i++) {
            if (muestras[i] > mayor) {
                mayor = muestras[i];
            }
        }
        return mayor + "";
    }

    public String reconocerImagen(BufferedImage imagen) {
        String resultado = "";
        InputStream imput = null;
        try {
            File file = new File("D:\\Diego C\\Desktop\\animals_net.nnet");
            imput = new FileInputStream(file);
        } catch (Exception e) {
        }
        // Carga de la Red Neuronal Entrenada con  Neuroph Estudio 
        NeuralNetwork nnet = NeuralNetwork.load(imput);
        // obtener el reconocimiento de la imagen de plugins de la red neuronal
        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);
        HashMap<String, Double> output = imageRecognition.recognizeImage(imagen);
//        double ar[] = {output.get("fish"), output.get("dog"), output.get("cat"), output.get("bird"), output.get("images")};
        resultado = output.toString();
        return resultado;
    }
}
